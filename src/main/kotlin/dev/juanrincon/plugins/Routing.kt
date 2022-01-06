package dev.juanrincon.plugins

import dev.juanrincon.controllers.exercisesController
import dev.juanrincon.controllers.typesController
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.content.*

fun Application.configureRouting() {
    

    routing {
        typesController()
        exercisesController()
        // Static plugin. Try to access `/static/index.html`
        static("api/gifs") {
            resources("gifs")
        }
        static("api/images") {
            resources("images")
        }
    }
}
