package app.tracktion.domain.daos

import app.tracktion.data.utilities.prependZeroes
import app.tracktion.domain.interfaces.Mapper
import app.tracktion.domain.models.Exercise
import app.tracktion.domain.models.Instruction
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Exercises : IntIdTable("exercise") {
    val name = varchar("name", 50)
    val imageUrl = varchar("imageUrl", 250).nullable()
    val videoUrl = varchar("videoUrl", 250).nullable()
    val categoryId = reference("categoryId", Categories)
    val muscleId = reference("muscleId", Muscles)
    val equipmentId = reference("equipmentId", Equipment)
    val splitId = reference("splitId", Splits)
}

class ExerciseDAO(id: EntityID<Int>) : IntEntity(id), Mapper<Exercise> {
    companion object : IntEntityClass<ExerciseDAO>(Exercises)

    var name by Exercises.name
    var imageUrl by Exercises.imageUrl
    var videoUrl by Exercises.videoUrl
    var category by CategoryDAO referencedOn Exercises.categoryId
    var muscle by MuscleDAO referencedOn Exercises.muscleId
    var equipment by EquipmentDAO referencedOn Exercises.equipmentId
    var split by SplitDAO referencedOn  Exercises.splitId
    private val instructions by InstructionDAO referrersOn Instructions.exerciseId

    override fun toModel() = Exercise(
        id.value,
        name,
        imageUrl,
        videoUrl,
        getInstructions(),
        muscle.toModel(),
        category.toModel(),
        equipment.toModel(),
        split.toModel()
    )

    fun toModel(host: String) = Exercise(
        id.value,
        name,
        imageUrl,
        "$host/api/gifs/${id.value.prependZeroes()}",
        getInstructions(),
        muscle.toModel(),
        category.toModel(),
        equipment.toModel(),
        split.toModel()
    )

    private fun getInstructions(): List<Instruction>? {
        return if (instructions.empty()) {
            null
        } else {
            instructions.map { it.toModel() }
        }
    }
}