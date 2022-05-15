package app.tracktion.domain.models

import io.ktor.server.auth.*
import java.io.Serializable

data class User(
    val id: Int,
    val email: String,
    val displayName: String,
    val imageUrl: String?,
    val firstName: String?,
    val lastName: String?,
    val phone: Long?,
    val countryCode: Int?,
    val password: String,
    var passwordHash: String = ""
) : Serializable, Principal