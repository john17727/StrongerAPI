package app.tracktion.data.repositories

import app.tracktion.domain.daos.MuscleDAO
import app.tracktion.domain.interfaces.ReadRepository
import app.tracktion.domain.models.Muscle
import app.tracktion.plugins.dbQuery

class MuscleRepository : ReadRepository<Muscle> {
    override suspend fun findById(id: Int) = dbQuery {
        getDAOById(id)?.toModel()
    }

    override suspend fun getAll() = dbQuery {
        MuscleDAO.all().map { it.toModel() }
    }

    override suspend fun getAllPaginated(limit: Int, offset: Long) = getAll()

    override suspend fun getCount() = dbQuery {
        MuscleDAO.count()
    }

    private fun getDAOById(id: Int) = MuscleDAO.findById(id)
}