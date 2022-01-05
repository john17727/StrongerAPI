package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.*
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.Exercise
import org.jetbrains.exposed.sql.transactions.transaction

class ExerciseRepository : Repository<Exercise> {
    override fun getById(id: Int) = transaction {
        getDAOById(id)?.toModel()
    }

    override fun getAll() = transaction {
        ExerciseDAO.all().map { it.toModel() }
    }

    override fun delete(id: Int) = transaction {
        getDAOById(id)?.let {
            it.delete()
            true
        } ?: false
    }

    override fun add(entry: Exercise) = transaction {

        val categoryDAO = CategoryDAO.findById(entry.category.id)
        val muscleDAO = MuscleDAO.findById(entry.muscle.id)

        val exerciseDAO = ExerciseDAO.new {
            name = entry.name
            imageUrl = entry.imageUrl
            videoUrl = entry.videoUrl
            category = categoryDAO!!
            muscle = muscleDAO!!
        }

        entry.instructions?.forEach { instruction ->
            InstructionDAO.new {
                exercise = exerciseDAO
                step = instruction.step
                text = instruction.text
            }
        }

        exerciseDAO.toModel()
    }

    override fun update(entry: Exercise) = transaction {
        val exerciseDAO = getDAOById(entry.id)

        val categoryDAO = CategoryDAO.findById(entry.category.id)
        val muscleDAO = MuscleDAO.findById(entry.muscle.id)

        exerciseDAO?.name = entry.name
        exerciseDAO?.imageUrl = entry.imageUrl
        exerciseDAO?.videoUrl = entry.videoUrl
        exerciseDAO?.category = categoryDAO!!
        exerciseDAO?.muscle = muscleDAO!!

        val currentInstructions = InstructionDAO.find { Instructions.exerciseId eq entry.id }

        entry.instructions?.forEach { instruction ->
            val instructionDAO = currentInstructions.find { it.step == instruction.step }

            instructionDAO?.let {
                it.exercise = exerciseDAO!!
                it.step = instruction.step
                it.text = instruction.text
            } ?: InstructionDAO.new {
                exercise = exerciseDAO!!
                step = instruction.step
                text = instruction.text
            }
        }

        exerciseDAO?.toModel()
    }

    private fun getDAOById(id: Int) = ExerciseDAO.findById(id)
}