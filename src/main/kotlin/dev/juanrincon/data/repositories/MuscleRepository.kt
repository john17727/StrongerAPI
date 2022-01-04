package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.Categories
import dev.juanrincon.domain.daos.Muscles
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.Muscle
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class MuscleRepository: Repository<Muscle> {
    override fun getById(id: Int) = transaction {
        Muscles.select { Muscles.id eq id }.map {
            Muscle(
                it[Muscles.id].value,
                it[Muscles.name],
                it[Muscles.imageUrl]
            )
        }.first()
    }

    override fun getAll() = transaction {
        Muscles.selectAll().map {
            Muscle(
                it[Muscles.id].value,
                it[Muscles.name],
                it[Muscles.imageUrl]
            )
        }
    }

    override fun delete(id: Int) = transaction {
        0 < Muscles.deleteWhere { Muscles.id eq id }
    }

    override fun add(entry: Muscle) = transaction {
        val id = Muscles.insertAndGetId {
            it[name] = entry.name
            it[imageUrl] = entry.imageUrl
        }

        getById(id.value)
    }

    override fun update(entry: Muscle) = transaction {
        Categories.update({ Categories.id eq entry.id }) {
            it[name] = entry.name
            it[imageUrl] = entry.imageUrl
        }

        entry.id?.let {
            getById(it)
        }?: entry
    }
}