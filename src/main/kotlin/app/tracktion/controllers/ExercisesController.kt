package app.tracktion.controllers

import app.tracktion.data.services.ExerciseService
import app.tracktion.data.state.ServiceResponse
import app.tracktion.data.state.ServiceResponse.Success
import app.tracktion.data.utilities.Constants
import app.tracktion.data.utilities.Constants.LIMIT
import app.tracktion.data.utilities.UtilityTools
import app.tracktion.domain.models.ApiResponse
import app.tracktion.domain.models.routing.Exercises
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.exercisesController(exerciseService: ExerciseService) {

    get<Exercises> {
        val limit = call.request.queryParameters["limit"]?.toInt() ?: LIMIT
        val offset = call.request.queryParameters["offset"]?.toLong() ?: 0
        when (val response = exerciseService.getAllExercises(limit, offset)) {
            is Success -> call.respond(
                response.status,
                ApiResponse.success(
                    response.data,
                    limit,
                    UtilityTools.getPreviousOffset(limit, offset),
                    UtilityTools.getNextOffset(limit, offset, exerciseService.getCount())
                )
            )
            is ServiceResponse.Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }
}