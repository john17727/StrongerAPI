package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.*
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.Exercise
import dev.juanrincon.plugins.dbQuery

class ExerciseRepository : Repository<Exercise> {
    override suspend fun findById(id: Int) = dbQuery {
        getDAOById(id)?.toModel()
    }

    override suspend fun getAll() = dbQuery {
        ExerciseDAO.all().map { it.toModel() }
    }

    override suspend fun delete(id: Int) = dbQuery {
        getDAOById(id)?.let {
            it.delete()
            true
        } ?: false
    }

    override suspend fun add(entry: Exercise) = dbQuery {

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

    override suspend fun update(entry: Exercise) = dbQuery {
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