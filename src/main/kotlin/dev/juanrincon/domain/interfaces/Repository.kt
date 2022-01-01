package dev.juanrincon.domain.interfaces

interface Repository<T> {
    fun getById(id: Int): T

    fun getAll(): List<T>

    fun delete(id: Int): Boolean

    fun add(entry: T): T

    fun update(entry: T): T
}