package app.tracktion.domain.interfaces

interface ReadRepository<T> {
    suspend fun findById(id: Int): T?

    suspend fun getAll(): List<T>

    suspend fun getAllPaginated(limit: Int, offset: Long): List<T>

    suspend fun getCount(): Long
}