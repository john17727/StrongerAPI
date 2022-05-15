package app.tracktion.data.services

import app.tracktion.data.state.ServiceResponse
import app.tracktion.domain.interfaces.Repository
import app.tracktion.domain.models.Muscle
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