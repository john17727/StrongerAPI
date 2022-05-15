package app.tracktion.domain.interfaces

interface Repository<T> {
    suspend fun findById(id: Int): T?

    suspend fun getAll(): List<T>

    suspend fun delete(id: Int): Boolean

    suspend fun add(entry: T): T

    suspend fun update(entry: T): T?
}