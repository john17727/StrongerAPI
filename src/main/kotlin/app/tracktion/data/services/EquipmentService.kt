package app.tracktion.data.services

import app.tracktion.data.state.ServiceResponse
import app.tracktion.domain.interfaces.ReadRepository
import app.tracktion.domain.models.Equipment
import io.ktor.http.*

class EquipmentService(private val repository: ReadRepository<Equipment>) {

    suspend fun getAllEquipmentTypes(): ServiceResponse<List<Equipment>> {
        val equipment = repository.getAll()

        return if (equipment.isNotEmpty()) {
            ServiceResponse.Success(equipment)
        } else {
            ServiceResponse.Failed(HttpStatusCode.NotFound, "Failed to get equipment")
        }
    }
}