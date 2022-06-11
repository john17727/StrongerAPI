package app.tracktion.data.repositories

import app.tracktion.domain.daos.*
import app.tracktion.domain.interfaces.ReadRepository
import app.tracktion.domain.interfaces.Repository
import app.tracktion.domain.models.Exercise
import app.tracktion.plugins.dbQuery

class ExerciseRepository : ReadRepository<Exercise> {
    override suspend fun findById(id: Int) = dbQuery {
        getDAOById(id)?.toModel()
    }

    override suspend fun getAll() = dbQuery {
        ExerciseDAO.all().map { it.toModel() }
    }

    private fun getDAOById(id: Int) = ExerciseDAO.findById(id)
}