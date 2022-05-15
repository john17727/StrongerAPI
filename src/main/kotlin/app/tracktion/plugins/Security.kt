package app.tracktion.plugins

import app.tracktion.data.services.JwtService
import app.tracktion.domain.interfaces.Repository
import app.tracktion.domain.models.StrongerSession
import app.tracktion.domain.models.User
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
                val user = repo.findById(claimString)
                user
            }
        }
    }
}
