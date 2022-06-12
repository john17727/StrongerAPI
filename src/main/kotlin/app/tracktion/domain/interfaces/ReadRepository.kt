package app.tracktion.domain.interfaces

interface ReadRepository<T> {
    suspend fun findById(id: Int): T?

    suspend fun getAll(): List<T>
}