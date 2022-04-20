package dev.juanrincon.plugins

import dev.juanrincon.data.services.JwtService
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.StrongerSession
import dev.juanrincon.domain.models.User
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.sessions.*

fun Application.configureSecurity(repo: Repository<User>, jwtService: JwtService) {
    install(Sessions) {
        cookie<StrongerSession>("STRONGER_SESSION") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    authentication {
        jwt("jwt") {
            verifier(jwtService.verifier)
            realm = "Stronger"
            validate {
                val payload = it.payload
                val claim = payload.getClaim("id")
                val claimString = claim.asInt()
                val user = repo.getById(claimString)
                user
            }
        }
    }
}
