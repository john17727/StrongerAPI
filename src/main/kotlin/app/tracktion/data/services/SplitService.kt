package app.tracktion.data.services

import app.tracktion.data.state.ServiceResponse
import app.tracktion.domain.interfaces.ReadRepository
import app.tracktion.domain.models.Split
import io.ktor.http.*

class SplitService(private val repository: ReadRepository<Split>) {

    suspend fun getAllSplitTypes(): ServiceResponse<List<Split>> {
        val splits = repository.getAll()

        return if (splits.isNotEmpty()) {
            ServiceResponse.Success(splits)
        } else {
            ServiceResponse.Failed(HttpStatusCode.NotFound, "Failed to get splits")
        }
    }
}