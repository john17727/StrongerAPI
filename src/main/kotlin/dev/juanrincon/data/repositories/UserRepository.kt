package dev.juanrincon.data.repositories

import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.User

class UserRepository: Repository<User> {
    override fun getById(id: Int): User? {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<User> {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(entry: User): User? {
        TODO("Not yet implemented")
    }

    override fun add(entry: User): User {
        TODO("Not yet implemented")
    }
}