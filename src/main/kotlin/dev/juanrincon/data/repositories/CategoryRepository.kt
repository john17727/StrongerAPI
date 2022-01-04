package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.Categories
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.Category
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class CategoryRepository : Repository<Category> {
    override fun getById(id: Int) = transaction {
        Categories.select { Categories.id eq id }.map {
            Category(
                it[Categories.id].value,
                it[Categories.name],
                it[Categories.imageUrl]
            )
        }.first()
    }


    override fun getAll() = transaction {
        Categories.selectAll().map {
            Category(
                it[Categories.id].value,
                it[Categories.name],
                it[Categories.imageUrl]
            )
        }
    }

    override fun delete(id: Int) = transaction {
        0 < Categories.deleteWhere { Categories.id eq id }
    }

    override fun add(entry: Category) = transaction {
        val id = Categories.insertAndGetId {
            it[name] = entry.name
            it[imageUrl] = entry.imageUrl
        }
        getById(id.value)
    }

    override fun update(entry: Category) = transaction {
        Categories.update({ Categories.id eq entry.id }) {
            it[name] = entry.name
            it[imageUrl] = entry.imageUrl
        }

        entry.id?.let {
            getById(it)
        }?: entry
    }
}