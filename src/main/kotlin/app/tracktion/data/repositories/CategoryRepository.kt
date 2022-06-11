package app.tracktion.data.repositories

import app.tracktion.domain.daos.CategoryDAO
import app.tracktion.domain.interfaces.ReadRepository
import app.tracktion.domain.models.Category
import org.jetbrains.exposed.sql.transactions.transaction

class CategoryRepository : ReadRepository<Category> {
    override suspend fun findById(id: Int) = transaction {
        getDAOById(id)?.toModel()
    }


    override suspend fun getAll() = transaction {
        CategoryDAO.all().map { it.toModel() }
    }

    private fun getDAOById(id: Int) = CategoryDAO.findById(id)
}