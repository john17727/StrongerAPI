package dev.juanrincon.controllers

import dev.juanrincon.data.services.ExerciseService
import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.exercisesController() {
    val service: ExerciseService by inject()

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