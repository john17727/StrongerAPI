package dev.juanrincon.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val success: Boolean,
    val message: String? = null,
    val previousPage: Int? = null,
    val nextPage: Int? = null,
    val data: T? = null
) {
    companion object {
        fun <T> success(
            data: T,
            previousPage: Int? = null,
            nextPage: Int? = null
        ): ApiResponse<T> {
            return ApiResponse(
                success = true,
                data = data,
                previousPage = previousPage,
                nextPage = nextPage
            )
        }

        fun <T> error(
            message: String
        ): ApiResponse<T> {
            return ApiResponse(
                success = false,
                message = message
            )
        }
    }
}
