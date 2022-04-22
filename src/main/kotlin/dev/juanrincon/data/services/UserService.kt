package dev.juanrincon.data.services

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
        try {
            return ServiceResponse.Success(HttpStatusCode.Created, repository.add(user))
        } catch (e: Throwable) {
            return ServiceResponse.Failed(HttpStatusCode.BadRequest, "Problems creating User")
        }
    }
}