package dev.juanrincon.controllers

import dev.juanrincon.data.services.CategoryService
import dev.juanrincon.data.services.MuscleService
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
            val response = categoryService.getAllCategories()
            if (response.success) {
                call.respond(OK, response)
            } else {
                call.respond(NotFound, response)
            }
        }
    }

    route("/api/types/muscle") {
        get{
            val response = muscleService.getAllMuscleTypes()
            if (response.success) {
                call.respond(OK, response)
            } else {
                call.respond(NotFound, response)
            }
        }
    }
}