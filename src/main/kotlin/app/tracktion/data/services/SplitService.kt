package app.tracktion.data.services

import app.tracktion.data.state.ServiceResponse
import app.tracktion.domain.interfaces.TypeRepository
import app.tracktion.domain.models.Exercise
import app.tracktion.domain.models.Split
import io.ktor.http.*

class SplitService(private val repository: TypeRepository<Split>) {

    suspend fun getAllSplitTypes(): ServiceResponse<List<Split>> {
        val splits = repository.getAll()

        return if (splits.isNotEmpty()) {
            ServiceResponse.Success(splits)
        } else {
            ServiceResponse.Failed(HttpStatusCode.NotFound, "Failed to get splits")
        }
    }

    suspend fun getExercisesForSplit(name: String, limit: Int, offset: Long): ServiceResponse<List<Exercise>> {
        val exercises = repository.getExercisesFor(name, limit, offset)

        return if (exercises.isNotEmpty()) {
            ServiceResponse.Success(exercises)
        } else {
            if (offset > 0) {
                ServiceResponse.Failed(HttpStatusCode.NotFound, "There are no more exercises for offset $offset")
            } else {
                ServiceResponse.Failed(HttpStatusCode.NotFound, "No exercises exist for $name split")
            }
        }
    }

    suspend fun getExerciseCountFor(name: String) = repository.getExerciseCountFor(name)
}