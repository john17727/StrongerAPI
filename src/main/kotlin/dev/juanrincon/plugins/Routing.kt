package dev.juanrincon.plugins

import dev.juanrincon.controllers.exercisesController
import dev.juanrincon.controllers.typesController
import dev.juanrincon.data.services.JwtService
import dev.juanrincon.di.MainModule
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.User
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.locations.*
import io.ktor.server.routing.*

@KtorExperimentalLocationsAPI
fun Application.configureRouting(userRepository: Repository<User>, jwtService: JwtService) {
    val categoryService = MainModule.getCategoryService()
    val muscleService = MainModule.getMuscleService()
    install(Locations)
    routing {
        typesController(categoryService, muscleService)
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
