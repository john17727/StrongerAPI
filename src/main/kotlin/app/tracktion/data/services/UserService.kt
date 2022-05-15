package app.tracktion.data.services

import app.tracktion.data.repositories.UserRepository
import app.tracktion.data.state.ServiceResponse
import app.tracktion.data.utilities.hash
import app.tracktion.domain.interfaces.Repository
import app.tracktion.domain.models.User
import io.ktor.http.*
import io.ktor.server.application.*

class UserService(val repository: Repository<User>, private val environment: ApplicationEnvironment) {

    suspend fun addNewUser(user: User): ServiceResponse<User> {
        if (user.email.isEmpty() || user.displayName.isEmpty() || user.password.isEmpty()) return ServiceResponse.Failed(
            HttpStatusCode.BadRequest, "Missing Fields"
        )

        user.passwordHash = hash(user.password, environment)
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

        user.passwordHash = hash(user.password, environment)
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