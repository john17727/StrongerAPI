package app.tracktion.data.services

import app.tracktion.data.state.ServiceResponse
import app.tracktion.domain.interfaces.ReadRepository
import app.tracktion.domain.models.Category
import io.ktor.http.*

class CategoryService(private val repository: ReadRepository<Category>) {

    suspend fun getAllCategories(): ServiceResponse<List<Category>> {
        val categories = repository.getAll()

        return if (categories.isNotEmpty()) {
            ServiceResponse.Success(categories)
        } else {
            ServiceResponse.Failed(HttpStatusCode.NotFound, "Failed to get categories")
        }
    }
}