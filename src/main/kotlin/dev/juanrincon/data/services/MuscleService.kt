package dev.juanrincon.data.services

import dev.juanrincon.data.state.ServiceResponse
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.ApiResponse
import dev.juanrincon.domain.models.Muscle
import io.ktor.http.*

class MuscleService(private val repository: Repository<Muscle>) {

    suspend fun getAllMuscleTypes(): ServiceResponse<List<Muscle>> {
        val muscles = repository.getAll()

        return if (muscles.isNotEmpty()) {
            ServiceResponse.Success(muscles)
        } else {
            ServiceResponse.Failed(HttpStatusCode.NotFound, "Failed to get categories")
        }
    }
}