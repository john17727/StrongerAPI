package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.*
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.Exercise
import org.jetbrains.exposed.sql.*
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

        ExerciseDAO.new {
            name = entry.name
            imageUrl = entry.imageUrl
            videoUrl = entry.videoUrl
            category = categoryDAO!!
            muscle = muscleDAO!!
        }.toModel()
    }

    override fun update(entry: Exercise) = transaction {
        val exercise = getDAOById(entry.id)

        val categoryDAO = CategoryDAO.findById(entry.category.id)
        val muscleDAO = MuscleDAO.findById(entry.muscle.id)

        exercise?.name = entry.name
        exercise?.imageUrl = entry.imageUrl
        exercise?.videoUrl = entry.videoUrl
        exercise?.category = categoryDAO!!
        exercise?.muscle = muscleDAO!!

        exercise?.toModel()
    }

    private fun getDAOById(id: Int) = ExerciseDAO.findById(id)
}