package dev.juanrincon.plugins

import io.ktor.features.*
import io.ktor.application.*

fun Application.configureMonitoring() {
    install(CallLogging)
}
