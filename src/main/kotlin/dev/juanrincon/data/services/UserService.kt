package dev.juanrincon.data.services

import dev.juanrincon.data.repositories.UserRepository
import dev.juanrincon.data.state.ServiceResponse
import dev.juanrincon.data.utilities.hash
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

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
            if (currentUser.passwordHash == user.passwordHash) {
                ServiceResponse.Success(currentUser)
            } else {
                ServiceResponse.Failed(HttpStatusCode.BadRequest, "Problems retrieving User")
            }
        } catch (e: Throwable) {
            ServiceResponse.Failed(HttpStatusCode.BadRequest, "Problems retrieving User")
        }
    }
}