package dev.juanrincon.domain.daos

import dev.juanrincon.domain.interfaces.Mapper
import dev.juanrincon.domain.models.Exercise
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Exercises : IntIdTable() {
    val name = varchar("name", 50)
    val imageUrl = varchar("imageUrl", 250).nullable()
    val videoUrl = varchar("videoUrl", 250).nullable()
    val categoryId = reference("categoryId", Categories)
    val muscleId = reference("muscleId", Muscles)
}

class ExerciseDAO(id: EntityID<Int>) : IntEntity(id), Mapper<Exercise> {
    companion object : IntEntityClass<ExerciseDAO>(Exercises)

    var name by Exercises.name
    var imageUrl by Exercises.imageUrl
    var videoUrl by Exercises.videoUrl
    var category by CategoryDAO referencedOn Exercises.categoryId
    var muscle by MuscleDAO referencedOn Exercises.muscleId

    override fun toModel() = Exercise(
        id.value,
        name,
        imageUrl,
        videoUrl,
        null,
        muscle.toModel(),
        category.toModel()
    )
}