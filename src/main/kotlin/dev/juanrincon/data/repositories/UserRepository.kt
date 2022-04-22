package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.UserDAO
import dev.juanrincon.domain.daos.Users
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.User
import dev.juanrincon.plugins.dbQuery

class UserRepository: Repository<User> {
    override suspend fun findById(id: Int) = dbQuery {
        getDAOById(id)?.toModel()
    }

    suspend fun findByEmail(email: String) = dbQuery {
        UserDAO.find { Users.email eq email }.first().toModel()
    }

    override suspend fun getAll() = dbQuery {
        UserDAO.all().map { it.toModel() }
    }

    override suspend fun delete(id: Int) = dbQuery {
        getDAOById(id)?.let {
            it.delete()
            true
        } ?: false
    }

    override suspend fun update(entry: User) = dbQuery {
        val user = getDAOById(entry.id)

        user?.email = entry.email
        user?.displayName = entry.displayName
        user?.firstName = entry.firstName
        user?.lastName = entry.lastName
        user?.phone = entry.phone
        user?.countryCode = entry.countryCode
        user?.passwordHash = entry.passwordHash

        user?.toModel()
    }

    override suspend fun add(entry: User) = dbQuery {
        UserDAO.new {
            email = entry.email
            displayName = entry.displayName
            firstName = entry.firstName
            lastName = entry.lastName
            phone = entry.phone
            countryCode = entry.countryCode
            passwordHash = entry.passwordHash
        }.toModel()
    }

    private fun getDAOById(id: Int) = UserDAO.findById(id)
}