package dev.juanrincon.domain.daos

import dev.juanrincon.domain.interfaces.Mapper
import dev.juanrincon.domain.models.Instruction
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Instructions: IntIdTable() {
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