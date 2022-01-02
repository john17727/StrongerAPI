package dev.juanrincon.plugins

import dev.juanrincon.di.mainModule
import io.ktor.application.*
import org.koin.ktor.ext.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        modules(mainModule)
        slf4jLogger()
    }
}