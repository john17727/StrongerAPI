package dev.juanrincon

import dev.juanrincon.data.repositories.UserRepository
import dev.juanrincon.data.services.JwtService
import io.ktor.server.application.*
import dev.juanrincon.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureExposed()
    val userRepository = UserRepository()
    val jwtService = JwtService()
    configureSecurity(userRepository, jwtService)
    configureRouting(userRepository, jwtService)
    configureSerialization()
}
