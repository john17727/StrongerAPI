package dev.juanrincon.domain.daos

import dev.juanrincon.domain.interfaces.Mapper
import dev.juanrincon.domain.models.User
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Users: IntIdTable() {
    val firstName = varchar("firstName", 50).nullable()
    val lastName = varchar("lastName", 50).nullable()
    val displayName = varchar("displayName", 50)
    val imageUrl = varchar("imageUrl", 250).nullable()
    val email = varchar("email", 50)
    val phone = long("phone").nullable()
    val countryCode = integer("countryCode").nullable()
    val passwordHash = varchar("passwordHash", 250)
}

class UserDAO(id: EntityID<Int>): IntEntity(id), Mapper<User> {
    companion object : IntEntityClass<UserDAO>(Users)
    var firstName by Users.firstName
    var lastName by Users.lastName
    var displayName by Users.displayName
    var imageUrl by Users.imageUrl
    var email by Users.email
    var phone by Users.phone
    var countryCode by Users.countryCode
    var passwordHash by Users.passwordHash

    override fun toModel() = User(
        id.value,
        email,
        displayName,
        imageUrl,
        firstName,
        lastName,
        phone,
        countryCode,
        passwordHash
    )

}