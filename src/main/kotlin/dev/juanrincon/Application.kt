package dev.juanrincon

import io.ktor.application.*
import dev.juanrincon.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureExposed()
    configureKoin()
    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureSecurity()
}
