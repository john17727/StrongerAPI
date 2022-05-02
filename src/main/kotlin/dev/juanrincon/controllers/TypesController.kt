package dev.juanrincon.controllers

import dev.juanrincon.API_VERSION
import dev.juanrincon.data.services.CategoryService
import dev.juanrincon.data.services.MuscleService
import dev.juanrincon.data.state.ServiceResponse.Failed
import dev.juanrincon.data.state.ServiceResponse.Success
import dev.juanrincon.domain.models.ApiResponse
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

const val TYPES = "$API_VERSION/types"
const val CATEGORIES = "$TYPES/categories"
const val MUSCLES = "$TYPES/muscles"

@KtorExperimentalLocationsAPI
@Location(CATEGORIES)
class CategoriesRoute

@KtorExperimentalLocationsAPI
@Location(MUSCLES)
class MusclesRoute

@KtorExperimentalLocationsAPI
fun Route.typesController(
    categoryService: CategoryService,
    muscleService: MuscleService
) {
    get<CategoriesRoute> {
        when (val response = categoryService.getAllCategories()) {
            is Success -> call.respond(response.status, ApiResponse.success(response.data))
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    get<MusclesRoute> {
        when (val response = muscleService.getAllMuscleTypes()) {
            is Success -> call.respond(response.status, ApiResponse.success(response.data))
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }
}