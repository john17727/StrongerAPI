package dev.juanrincon.controllers

import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.exercisesController() {

    route("api/exercise") {
        get {
            val response = service.getAllExercises()

            if (response.success) {
                call.respond(OK, response)
            } else {
                call.respond(NotFound, response)
            }
        }
    }
}