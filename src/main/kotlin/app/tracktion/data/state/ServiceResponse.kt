package app.tracktion.data.state

import io.ktor.http.*

sealed class ServiceResponse<out T : Any> {
    data class Success<out T : Any>(val data: T, val status: HttpStatusCode = HttpStatusCode.OK) : ServiceResponse<T>()
    data class Failed(val status: HttpStatusCode, val message: String) : ServiceResponse<Nothing>()
}
