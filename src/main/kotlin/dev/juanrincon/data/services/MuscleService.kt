package dev.juanrincon.data.services

import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.ApiResponse
import dev.juanrincon.domain.models.Muscle

class MuscleService(private val repository: Repository<Muscle>) {

    suspend fun getAllMuscleTypes(): ApiResponse<List<Muscle>> {
        val muscles = repository.getAll()

        return if (muscles.isNotEmpty()) {
            ApiResponse.success(muscles)
        } else {
            ApiResponse.error("Failed to get categories")
        }
    }
}