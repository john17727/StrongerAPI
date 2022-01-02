package dev.juanrincon.controllers

import dev.juanrincon.data.services.CategoryService
import dev.juanrincon.data.services.MuscleService
import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.typesController() {
    val categoryService: CategoryService by inject()
    val muscleService: MuscleService by inject()
    route("/api/types/category") {
        get {
            val response = categoryService.getAllCategories()
            if (response.success) {
                call.respond(OK, response)
            } else {
                call.respond(BadRequest, response)
            }
        }
    }

    route("/api/types/muscle") {
        get{
            val response = muscleService.getAllMuscleTypes()
            if (response.success) {
                call.respond(OK, response)
            } else {
                call.respond(BadRequest, response)
            }
        }
    }
}