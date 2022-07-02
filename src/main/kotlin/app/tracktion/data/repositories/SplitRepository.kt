package app.tracktion.data.repositories

import app.tracktion.domain.daos.SplitDAO
import app.tracktion.domain.daos.Splits
import app.tracktion.domain.interfaces.TypeRepository
import app.tracktion.domain.models.Split
import app.tracktion.plugins.dbQuery

class SplitRepository : TypeRepository<Split> {
    override suspend fun getExercisesFor(name: String, limit: Int, offset: Long) = dbQuery {
        SplitDAO.find { Splits.name eq name }.first()
            .exercises.limit(limit, offset).map { it.toModel() }
    }

    override suspend fun getExerciseCountFor(name: String) = dbQuery {
        SplitDAO.find { Splits.name eq name }.first()
            .exercises.count()
    }
    override suspend fun findById(id: Int) = dbQuery {
        getDAOById(id)?.toModel()
    }

    override suspend fun getAll() = dbQuery {
        SplitDAO.all().map { it.toModel() }
    }

    override suspend fun getAllPaginated(limit: Int, offset: Long) = getAll()

    override suspend fun getCount() = dbQuery {
        SplitDAO.count()
    }

    private fun getDAOById(id: Int) = SplitDAO.findById(id)
}