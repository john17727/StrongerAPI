package app.tracktion.data.repositories

import app.tracktion.domain.daos.SplitDAO
import app.tracktion.domain.interfaces.ReadRepository
import app.tracktion.domain.models.Split
import app.tracktion.plugins.dbQuery

class SplitRepository : ReadRepository<Split> {
    override suspend fun findById(id: Int) = dbQuery {
        getDAOById(id)?.toModel()
    }

    override suspend fun getAll() = dbQuery {
        SplitDAO.all().map { it.toModel() }
    }

    private fun getDAOById(id: Int) = SplitDAO.findById(id)
}