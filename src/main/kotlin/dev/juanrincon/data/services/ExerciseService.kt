package dev.juanrincon.data.services

import dev.juanrincon.data.state.ServiceResponse
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.ApiResponse
import dev.juanrincon.domain.models.Exercise
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