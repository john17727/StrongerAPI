package app.tracktion.data.services

import app.tracktion.data.state.ServiceResponse
import app.tracktion.domain.interfaces.ReadRepository
import app.tracktion.domain.models.Exercise
import io.ktor.http.*

class ExerciseService(private val repository: ReadRepository<Exercise>) {

    suspend fun getAllExercises(limit: Int, offset: Long): ServiceResponse<List<Exercise>> {
        val exercises = repository.getAllPaginated(limit, offset)

        return if (exercises.isNotEmpty()) {
            ServiceResponse.Success(exercises)
        } else {
            ServiceResponse.Failed(HttpStatusCode.NotFound, "Failed to get exercises")
        }
    }
}