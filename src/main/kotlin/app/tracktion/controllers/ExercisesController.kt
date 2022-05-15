package app.tracktion.controllers

import app.tracktion.API_VERSION
import app.tracktion.data.services.ExerciseService
import app.tracktion.data.state.ServiceResponse
import app.tracktion.data.state.ServiceResponse.Success
import app.tracktion.domain.models.ApiResponse
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

const val EXERCISES = "$API_VERSION/exercises"

@KtorExperimentalLocationsAPI
@Location(EXERCISES)
class ExercisesRoute

fun Route.exercisesController(exerciseService: ExerciseService) {

    get<ExercisesRoute> {
        when (val response = exerciseService.getAllExercises()) {
            is Success -> call.respond(response.status, ApiResponse.success(response.data))
            is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }
}