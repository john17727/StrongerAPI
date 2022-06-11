package app.tracktion.domain.daos

import app.tracktion.domain.interfaces.Mapper
import app.tracktion.domain.models.Instruction
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Instructions: IntIdTable("instruction") {
    val exerciseId = reference("exerciseId", Exercises)
    val step = integer("step")
    val text = varchar("text", 500)
}

class InstructionDAO(id: EntityID<Int>) : IntEntity(id), Mapper<Instruction> {
    companion object : IntEntityClass<InstructionDAO>(Instructions)

    var exercise by ExerciseDAO referencedOn Instructions.exerciseId
    var step by Instructions.step
    var text by Instructions.text

    override fun toModel() = Instruction(step, text)
}