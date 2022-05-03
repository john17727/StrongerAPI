package dev.juanrincon.data.services

import dev.juanrincon.data.repositories.UserRepository
import dev.juanrincon.data.state.ServiceResponse
import dev.juanrincon.data.utilities.hash
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.User
import io.ktor.http.*

class UserService(val repository: Repository<User>) {

    suspend fun addNewUser(user: User): ServiceResponse<User> {
        if (user.email.isEmpty() || user.displayName.isEmpty() || user.password.isEmpty()) return ServiceResponse.Failed(
            HttpStatusCode.BadRequest, "Missing Fields"
        )

        user.passwordHash = hash(user.password)
        return try {
            ServiceResponse.Success(repository.add(user), HttpStatusCode.Created)
        } catch (e: Throwable) {
            ServiceResponse.Failed(HttpStatusCode.BadRequest, "Problems creating User")
        }
    }

    suspend fun loginUser(user: User): ServiceResponse<User> {
        if (user.email.isEmpty() || user.password.isEmpty()) return ServiceResponse.Failed(
            HttpStatusCode.BadRequest, "Missing Fields"
        )

        user.passwordHash = hash(user.password)
        return try {
            val currentUser = (repository as UserRepository).findByEmail(user.email)
            if (currentUser.password == user.passwordHash) {
                ServiceResponse.Success(currentUser)
            } else {
                ServiceResponse.Failed(
                    HttpStatusCode.BadRequest,
                    "Your login credentials don match an account in our system"
                )
            }
        } catch (e: Throwable) {
            ServiceResponse.Failed(HttpStatusCode.BadRequest, "There was a problem retrieving the user")
        }
    }
}