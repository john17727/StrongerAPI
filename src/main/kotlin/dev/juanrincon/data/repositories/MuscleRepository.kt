package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.MuscleDAO
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.Muscle
import org.jetbrains.exposed.sql.transactions.transaction

class MuscleRepository : Repository<Muscle> {
    override fun getById(id: Int) = transaction {
        getDAOById(id)?.toModel()
    }

    override fun getAll() = transaction {
        MuscleDAO.all().map { it.toModel() }
    }

    override fun delete(id: Int) = transaction {
        getDAOById(id)?.let {
            it.delete()
            true
        } ?: false
    }

    override fun add(entry: Muscle) = transaction {
        MuscleDAO.new {
            name = entry.name
            imageUrl = entry.imageUrl
        }.toModel()
    }

    override fun update(entry: Muscle) = transaction {
        val muscle = getDAOById(entry.id)

        muscle?.name = entry.name
        muscle?.imageUrl = entry.imageUrl

        muscle?.toModel()
    }

    private fun getDAOById(id: Int) = MuscleDAO.findById(id)
}