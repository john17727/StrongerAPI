package dev.juanrincon.domain.models


data class ApiResponse<T>(
    val status: String,
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
        ) = ApiResponse(
            status = "success",
            data = data,
            previousPage = previousPage,
            nextPage = nextPage
        )

        fun fail(message: String) = ApiResponse<Nothing>(
            status = "fail",
            message = message
        )

        fun error(
            message: String
        ) = ApiResponse<Nothing>(
            status = "error",
            message = message
        )
    }
}
