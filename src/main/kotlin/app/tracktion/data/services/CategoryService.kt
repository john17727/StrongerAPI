package app.tracktion.data.services

import app.tracktion.data.state.ServiceResponse
import app.tracktion.domain.models.Category
import app.tracktion.domain.interfaces.Repository
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