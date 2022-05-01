package dev.juanrincon.data.services

import dev.juanrincon.data.state.ServiceResponse
import dev.juanrincon.domain.models.ApiResponse
import dev.juanrincon.domain.models.Category
import dev.juanrincon.domain.interfaces.Repository
import io.ktor.http.*

class CategoryService(private val repository: Repository<Category>) {

    suspend fun getAllCategories(): ServiceResponse<List<Category>> {
        val categories = repository.getAll()

        return if (categories.isNotEmpty()) {
            ServiceResponse.Success(categories)
        } else {
            ServiceResponse.Failed(HttpStatusCode.NotFound, "Failed to get categories")
        }
    }
}