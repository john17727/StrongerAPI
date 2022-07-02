package app.tracktion.data.services

import app.tracktion.data.state.ServiceResponse
import app.tracktion.domain.interfaces.TypeRepository
import app.tracktion.domain.models.Equipment
import app.tracktion.domain.models.Exercise
import io.ktor.http.*

class EquipmentService(private val repository: TypeRepository<Equipment>) {

    suspend fun getAllEquipmentTypes(): ServiceResponse<List<Equipment>> {
        val equipment = repository.getAll()

        return if (equipment.isNotEmpty()) {
            ServiceResponse.Success(equipment)
        } else {
            ServiceResponse.Failed(HttpStatusCode.NotFound, "Failed to get equipment")
        }
    }

    suspend fun getExercisesForEquipment(name: String, limit: Int, offset: Long): ServiceResponse<List<Exercise>> {
        val exercises = repository.getExercisesFor(name, limit, offset)

        return if (exercises.isNotEmpty()) {
            ServiceResponse.Success(exercises)
        } else {
            if (offset > 0) {
                ServiceResponse.Failed(HttpStatusCode.NotFound, "There are no more exercises for offset $offset")
            } else {
                ServiceResponse.Failed(HttpStatusCode.NotFound, "No exercises exist for $name equipment")
            }
        }
    }

    suspend fun getExerciseCountFor(name: String) = repository.getExerciseCountFor(name)
}