package app.tracktion.data.services

import app.tracktion.data.repositories.ExerciseRepository
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

    suspend fun getExercisesById(id: Int): ServiceResponse<Exercise> {
        val exercise = repository.findById(id)

        return exercise?.let {
            ServiceResponse.Success(it)
        } ?: ServiceResponse.Failed(HttpStatusCode.NotFound, "No exercise with id $id was found")
    }

    suspend fun getCount() = repository.getCount()
}