package dev.juanrincon.controllers

import dev.juanrincon.data.services.CategoryService
import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.response.*
import io.ktor.routing.*

fun Route.typesController() {
    val service = CategoryService()
    route("/api/types/category") {
        get {
            val response = service.getAllCategories()
            if (response.success) {
                call.respond(OK, response)
            } else {
                call.respond(BadRequest, response)
            }
        }
    }
}