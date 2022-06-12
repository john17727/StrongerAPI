package app.tracktion.domain.interfaces

interface Repository<T> : ReadRepository<T> {

    suspend fun delete(id: Int): Boolean

    suspend fun add(entry: T): T

    suspend fun update(entry: T): T?
}