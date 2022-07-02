package app.tracktion.data.services

import app.tracktion.data.state.ServiceResponse
import app.tracktion.domain.interfaces.TypeRepository
import app.tracktion.domain.models.Category
import app.tracktion.domain.models.Exercise
import io.ktor.http.*

class CategoryService(private val repository: TypeRepository<Category>) {

    suspend fun getAllCategories(): ServiceResponse<List<Category>> {
        val categories = repository.getAll()

        return if (categories.isNotEmpty()) {
            ServiceResponse.Success(categories)
        } else {
            ServiceResponse.Failed(HttpStatusCode.NotFound, "Failed to get categories")
        }
    }

    suspend fun getExercisesForCategory(name: String, limit: Int, offset: Long): ServiceResponse<List<Exercise>> {
        val exercises = repository.getExercisesFor(name, limit, offset)

        return if (exercises.isNotEmpty()) {
            ServiceResponse.Success(exercises)
        } else {
            if (offset > 0) {
                ServiceResponse.Failed(HttpStatusCode.NotFound, "There are no more exercises for offset $offset")
            } else {
                ServiceResponse.Failed(HttpStatusCode.NotFound, "No exercises exist for $name category")
            }
        }
    }

    suspend fun getExerciseCountFor(name: String) = repository.getExerciseCountFor(name)
}