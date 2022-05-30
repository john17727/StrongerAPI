package app.tracktion

import app.tracktion.data.repositories.UserRepository
import app.tracktion.data.services.JwtService
import io.ktor.server.application.*
import app.tracktion.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureExposed(environment)

    val userRepository = UserRepository()
    val jwtService = JwtService(environment)
    configureSecurity(userRepository, jwtService)
    configureRouting(userRepository, jwtService)
    configureSerialization()
}
