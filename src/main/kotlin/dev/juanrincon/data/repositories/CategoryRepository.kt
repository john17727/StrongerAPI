package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.CategoryDAO
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.Category
import org.jetbrains.exposed.sql.transactions.transaction

class CategoryRepository : Repository<Category> {
    override fun getById(id: Int) = transaction {
        getDAOById(id)?.toModel()
    }


    override fun getAll() = transaction {
        CategoryDAO.all().map { it.toModel() }
    }

    override fun delete(id: Int) = transaction {
        getDAOById(id)?.let {
            it.delete()
            true
        } ?: false
    }

    override fun add(entry: Category) = transaction {
        CategoryDAO.new {
            name = entry.name
            imageUrl = entry.imageUrl
        }.toModel()
    }

    override fun update(entry: Category) = transaction {
        val category = getDAOById(entry.id)

        category?.name = entry.name
        category?.imageUrl = entry.imageUrl

        category?.toModel()
    }

    private fun getDAOById(id: Int) = CategoryDAO.findById(id)
}