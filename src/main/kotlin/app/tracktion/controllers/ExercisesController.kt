package app.tracktion.controllers

import app.tracktion.data.services.ExerciseService
import app.tracktion.data.state.ServiceResponse
import app.tracktion.data.state.ServiceResponse.Failed
import app.tracktion.data.state.ServiceResponse.Success
import app.tracktion.data.utilities.Constants
import app.tracktion.data.utilities.Constants.LIMIT
import app.tracktion.data.utilities.Constants.MAX_LIMIT
import app.tracktion.data.utilities.UtilityTools
import app.tracktion.domain.models.ApiResponse
import app.tracktion.domain.models.routing.Exercises
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.exercisesController(exerciseService: ExerciseService) {

    get<Exercises> {
        val limit = call.request.queryParameters["limit"]?.toInt() ?: LIMIT.let {
            if (it > MAX_LIMIT) {
                MAX_LIMIT
            } else {
                it
            }
        }
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
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    get<Exercises.Count> {
        call.respond(OK, ApiResponse.success(exerciseService.getCount()))
    }

    get<Exercises.Id> { exercise ->
        when (val response = exerciseService.getExercisesById(exercise.id)) {
            is Success -> call.respond(response.status, ApiResponse.success(response.data))
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }
}