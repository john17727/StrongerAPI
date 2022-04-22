package dev.juanrincon.data.services

import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.ApiResponse
import dev.juanrincon.domain.models.Exercise

class ExerciseService(private val repository: Repository<Exercise>) {

    suspend fun getAllExercises(): ApiResponse<List<Exercise>> {
        val exercises = repository.getAll()

        return if (exercises.isNotEmpty()) {
            ApiResponse.success(exercises)
        } else {
            ApiResponse.error("Failed to get exercises")
        }
    }
}