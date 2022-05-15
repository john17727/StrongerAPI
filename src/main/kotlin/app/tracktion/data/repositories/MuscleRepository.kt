package app.tracktion.data.repositories

import app.tracktion.domain.daos.MuscleDAO
import app.tracktion.domain.interfaces.Repository
import app.tracktion.domain.models.Muscle
import app.tracktion.plugins.dbQuery

class MuscleRepository : Repository<Muscle> {
    override suspend fun findById(id: Int) = dbQuery {
        getDAOById(id)?.toModel()
    }

    override suspend fun getAll() = dbQuery {
        MuscleDAO.all().map { it.toModel() }
    }

    override suspend fun delete(id: Int) = dbQuery {
        getDAOById(id)?.let {
            it.delete()
            true
        } ?: false
    }

    override suspend fun add(entry: Muscle) = dbQuery {
        MuscleDAO.new {
            name = entry.name
            imageUrl = entry.imageUrl
        }.toModel()
    }

    override suspend fun update(entry: Muscle) = dbQuery {
        val muscle = getDAOById(entry.id)

        muscle?.name = entry.name
        muscle?.imageUrl = entry.imageUrl

        muscle?.toModel()
    }

    private fun getDAOById(id: Int) = MuscleDAO.findById(id)
}