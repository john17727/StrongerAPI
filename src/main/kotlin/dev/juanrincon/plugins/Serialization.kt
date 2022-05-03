package dev.juanrincon.plugins

import io.ktor.shared.serialization.kotlinx.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson { }
    }
}
