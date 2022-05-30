package app.tracktion.controllers

import app.tracktion.data.services.ExerciseService
import app.tracktion.data.state.ServiceResponse
import app.tracktion.data.state.ServiceResponse.Success
import app.tracktion.domain.models.ApiResponse
import app.tracktion.domain.models.routing.Exercises
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.exercisesController(exerciseService: ExerciseService) {

    get<Exercises> {
        when (val response = exerciseService.getAllExercises()) {
            is Success -> call.respond(response.status, ApiResponse.success(response.data))
            is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }
}