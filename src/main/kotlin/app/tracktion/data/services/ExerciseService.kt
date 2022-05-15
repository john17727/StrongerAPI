package app.tracktion.data.services

import app.tracktion.data.state.ServiceResponse
import app.tracktion.domain.interfaces.Repository
import app.tracktion.domain.models.Exercise
import io.ktor.http.*

class ExerciseService(private val repository: Repository<Exercise>) {

    suspend fun getAllExercises(): ServiceResponse<List<Exercise>> {
        val exercises = repository.getAll()

        return if (exercises.isNotEmpty()) {
            ServiceResponse.Success(exercises)
        } else {
            ServiceResponse.Failed(HttpStatusCode.NotFound, "Failed to get exercises")
        }
    }
}