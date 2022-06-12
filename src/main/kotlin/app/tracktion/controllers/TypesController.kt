package app.tracktion.controllers

import app.tracktion.data.services.CategoryService
import app.tracktion.data.services.EquipmentService
import app.tracktion.data.services.MuscleService
import app.tracktion.data.services.SplitService
import app.tracktion.data.state.ServiceResponse.Failed
import app.tracktion.data.state.ServiceResponse.Success
import app.tracktion.domain.models.ApiResponse
import app.tracktion.domain.models.routing.Types
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

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

    get<Types.Muscles> {
        when (val response = muscleService.getAllMuscleTypes()) {
            is Success -> call.respond(response.status, ApiResponse.success(response.data))
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    get<Types.Equipment> {
        when (val response = equipmentService.getAllEquipmentTypes()) {
            is Success -> call.respond(response.status, ApiResponse.success(response.data))
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    get<Types.Splits> {
        when (val response = splitService.getAllSplitTypes()) {
            is Success -> call.respond(response.status, ApiResponse.success(response.data))
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }
}