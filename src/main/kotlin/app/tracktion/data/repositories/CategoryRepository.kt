package app.tracktion.data.repositories

import app.tracktion.domain.daos.Categories
import app.tracktion.domain.daos.CategoryDAO
import app.tracktion.domain.interfaces.ReadRepository
import app.tracktion.domain.interfaces.TypeRepository
import app.tracktion.domain.models.Category
import app.tracktion.domain.models.Exercise
import app.tracktion.plugins.dbQuery
import org.jetbrains.exposed.sql.transactions.transaction

class CategoryRepository : TypeRepository<Category> {
    override suspend fun getExercisesFor(name: String, limit: Int, offset: Long) = dbQuery {
        CategoryDAO.find { Categories.name eq name }.first()
            .exercises.limit(limit, offset).map { it.toModel() }
    }

    override suspend fun getExerciseCountFor(name: String) = dbQuery {
        CategoryDAO.find { Categories.name eq name }.first()
            .exercises.count()
    }

    override suspend fun findById(id: Int) = dbQuery {
        getDAOById(id)?.toModel()
    }


    override suspend fun getAll() = dbQuery {
        CategoryDAO.all().map { it.toModel() }
    }

    override suspend fun getAllPaginated(limit: Int, offset: Long) = getAll()

    override suspend fun getCount() = dbQuery {
        CategoryDAO.count()
    }

    private fun getDAOById(id: Int) = CategoryDAO.findById(id)
}