package dev.juanrincon.domain.interfaces

interface Mapper<model> {
    fun toModel(): model
}