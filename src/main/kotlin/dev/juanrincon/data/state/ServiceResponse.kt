package dev.juanrincon.data.state

import io.ktor.http.*

sealed class ServiceResponse<out T : Any> {
    data class Success<out T : Any>(val status: HttpStatusCode?, val data: T): ServiceResponse<T>()
    data class Failed(val status: HttpStatusCode, val message: String): ServiceResponse<Nothing>()
}
