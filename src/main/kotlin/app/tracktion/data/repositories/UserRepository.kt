package app.tracktion.data.repositories

import app.tracktion.domain.daos.UserDAO
import app.tracktion.domain.daos.Users
import app.tracktion.domain.interfaces.Repository
import app.tracktion.domain.models.User
import app.tracktion.plugins.dbQuery

class UserRepository: Repository<User> {
    override suspend fun findById(id: Int) = dbQuery {
        getDAOById(id)?.toModel()
    }

    suspend fun findByEmail(email: String) = dbQuery {
        UserDAO.find { Users.email eq email }.first().toModel()
    }

    suspend fun userExists(email: String) = dbQuery {
        UserDAO.find { Users.email eq email }.count() > 0
    }

    override suspend fun getAll() = dbQuery {
        UserDAO.all().map { it.toModel() }
    }

    override suspend fun getAllPaginated(limit: Int, offset: Long) = dbQuery {
        UserDAO.all().limit(limit, offset).map { it.toModel() }
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