package app.tracktion.controllers

import app.tracktion.data.services.CategoryService
import app.tracktion.data.services.EquipmentService
import app.tracktion.data.services.MuscleService
import app.tracktion.data.services.SplitService
import app.tracktion.data.state.ServiceResponse.Failed
import app.tracktion.data.state.ServiceResponse.Success
import app.tracktion.data.utilities.Constants
import app.tracktion.data.utilities.UtilityTools
import app.tracktion.data.utilities.UtilityTools.Companion.getNextOffset
import app.tracktion.data.utilities.UtilityTools.Companion.getPreviousOffset
import app.tracktion.data.utilities.getLimit
import app.tracktion.domain.models.ApiResponse
import app.tracktion.domain.models.routing.Types
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

fun Route.typesController(
    categoryService: CategoryService,
    muscleService: MuscleService,
    equipmentService: EquipmentService,
    splitService: SplitService
) {
    get<Types.Categories> {
        when (val response = categoryService.getAllCategories()) {
            is Success -> call.respond(response.status, ApiResponse.success(response.data))
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    get<Types.Categories.Exercises> { category ->
        val limit = getLimit()
        val offset = call.request.queryParameters["offset"]?.toLong() ?: 0

        val name = category.name.replace('_', ' ')

        when (val response = categoryService.getExercisesForCategory(name, limit, offset)) {
            is Success -> {
                val count = categoryService.getExerciseCountFor(name)
                call.respond(
                    response.status,
                    ApiResponse.success(
                        response.data,
                        count,
                        limit,
                        getPreviousOffset(limit, offset),
                        getNextOffset(limit, offset, count)
                    )
                )
            }
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    get<Types.Muscles> {
        when (val response = muscleService.getAllMuscleTypes()) {
            is Success -> call.respond(response.status, ApiResponse.success(response.data))
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    get<Types.Muscles.Exercises> { category ->
        val limit = getLimit()
        val offset = call.request.queryParameters["offset"]?.toLong() ?: 0

        val name = category.name.replace('_', ' ')

        when (val response = muscleService.getExercisesForMuscle(name, limit, offset)) {
            is Success -> {
                val count = muscleService.getExerciseCountFor(name)
                call.respond(
                    response.status,
                    ApiResponse.success(
                        response.data,
                        count,
                        limit,
                        getPreviousOffset(limit, offset),
                        getNextOffset(limit, offset, count)
                    )
                )
            }
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    get<Types.Equipment> {
        when (val response = equipmentService.getAllEquipmentTypes()) {
            is Success -> call.respond(response.status, ApiResponse.success(response.data))
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    get<Types.Equipment.Exercises> { category ->
        val limit = getLimit()
        val offset = call.request.queryParameters["offset"]?.toLong() ?: 0

        val name = category.name.replace('_', ' ')

        when (val response = equipmentService.getExercisesForEquipment(name, limit, offset)) {
            is Success -> {
                val count = equipmentService.getExerciseCountFor(name)
                call.respond(
                    response.status,
                    ApiResponse.success(
                        response.data,
                        count,
                        limit,
                        getPreviousOffset(limit, offset),
                        getNextOffset(limit, offset, count)
                    )
                )
            }
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    get<Types.Splits> {
        when (val response = splitService.getAllSplitTypes()) {
            is Success -> call.respond(response.status, ApiResponse.success(response.data))
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    get<Types.Splits.Exercises> { category ->
        val limit = getLimit()
        val offset = call.request.queryParameters["offset"]?.toLong() ?: 0

        val name = category.name.replace('_', ' ')

        when (val response = splitService.getExercisesForSplit(name, limit, offset)) {
            is Success -> {
                val count = splitService.getExerciseCountFor(name)
                call.respond(
                    response.status,
                    ApiResponse.success(
                        response.data,
                        count,
                        limit,
                        getPreviousOffset(limit, offset),
                        getNextOffset(limit, offset, count)
                    )
                )
            }
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }
}