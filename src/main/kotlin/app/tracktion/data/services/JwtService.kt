package app.tracktion.data.services

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import app.tracktion.domain.models.User
import io.ktor.server.application.*
import java.util.*

class JwtService(environment: ApplicationEnvironment) {

    private val issuer = "todoServer"
    private val jwtSecret = environment.config.propertyOrNull("ktor.database.driver")?.getString()
    private val algorithm = Algorithm.HMAC512(jwtSecret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun generateToken(user: User): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("id", user.id)
        .withExpiresAt(expiresAt())
        .sign(algorithm)

    private fun expiresAt() =
        Date(System.currentTimeMillis() + 3_600_000 * 24) // 24 hours
}