package app.tracktion.domain.interfaces

interface Mapper<model> {
    fun toModel(): model
}