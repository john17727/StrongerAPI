package app.tracktion.domain.interfaces

import app.tracktion.domain.models.Exercise

interface TypeRepository<T> : ReadRepository<T> {
     suspend fun getExercisesFor(name: String, limit: Int, offset: Long): List<Exercise>

     suspend fun getExerciseCountFor(name: String): Long
}