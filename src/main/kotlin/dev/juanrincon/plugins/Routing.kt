package dev.juanrincon.plugins

import dev.juanrincon.controllers.typesController
import io.ktor.routing.*
import io.ktor.application.*

fun Application.configureRouting() {
    

    routing {
        typesController()
        // Static plugin. Try to access `/static/index.html`
//        static("/static") {
//            resources("static")
//        }
    }
}
