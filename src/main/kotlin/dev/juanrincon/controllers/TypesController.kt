package dev.juanrincon.controllers

import dev.juanrincon.data.services.CategoryService
import dev.juanrincon.data.services.MuscleService
import dev.juanrincon.data.state.ServiceResponse
import dev.juanrincon.data.state.ServiceResponse.Failed
import dev.juanrincon.data.state.ServiceResponse.Success
import dev.juanrincon.domain.models.ApiResponse
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.typesController(
    categoryService: CategoryService,
    muscleService: MuscleService
) {
    route("/api/types/category") {
        get {
            when (val response = categoryService.getAllCategories()) {
                is Success -> call.respond(response.status, ApiResponse.success(response.data))
                is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
            }
        }
    }

    route("/api/types/muscle") {
        get {
            when (val response = muscleService.getAllMuscleTypes()) {
                is Success -> call.respond(response.status, ApiResponse.success(response.data))
                is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
            }
        }
    }
}