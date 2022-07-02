package app.tracktion.data.services

import app.tracktion.data.state.ServiceResponse
import app.tracktion.domain.interfaces.TypeRepository
import app.tracktion.domain.models.Exercise
import app.tracktion.domain.models.Muscle
import io.ktor.http.*

class MuscleService(private val repository: TypeRepository<Muscle>) {

    suspend fun getAllMuscleTypes(): ServiceResponse<List<Muscle>> {
        val muscles = repository.getAll()

        return if (muscles.isNotEmpty()) {
            ServiceResponse.Success(muscles)
        } else {
            ServiceResponse.Failed(HttpStatusCode.NotFound, "Failed to get categories")
        }
    }

    suspend fun getExercisesForMuscle(name: String, limit: Int, offset: Long): ServiceResponse<List<Exercise>> {
        val exercises = repository.getExercisesFor(name, limit, offset)

        return if (exercises.isNotEmpty()) {
            ServiceResponse.Success(exercises)
        } else {
            if (offset > 0) {
                ServiceResponse.Failed(HttpStatusCode.NotFound, "There are no more exercises for offset $offset")
            } else {
                ServiceResponse.Failed(HttpStatusCode.NotFound, "No exercises exist for $name muscles")
            }
        }
    }

    suspend fun getExerciseCountFor(name: String) = repository.getExerciseCountFor(name)
}