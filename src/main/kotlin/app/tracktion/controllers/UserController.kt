package app.tracktion.controllers

import app.tracktion.data.services.JwtService
import app.tracktion.data.services.UserService
import app.tracktion.data.state.ServiceResponse.Failed
import app.tracktion.data.state.ServiceResponse.Success
import app.tracktion.domain.models.ApiResponse
import app.tracktion.domain.models.StrongerSession
import app.tracktion.domain.models.Token
import app.tracktion.domain.models.User
import app.tracktion.domain.models.routing.Users
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.userController(
    userService: UserService,
    jwtService: JwtService
) {
    post<Users.New> {
        val user = call.receive<User>()
        when (val response = userService.addNewUser(user)) {
            is Success -> {
                val currentUser = response.data
                call.sessions.set(StrongerSession(currentUser.id))
                call.respond(response.status, ApiResponse.success(Token(jwtService.generateToken(currentUser))))
            }
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }

    post<Users.Login> {
        val user = call.receive<User>()
        when (val response = userService.loginUser(user)) {
            is Success -> {
                val currentUser = response.data
                call.sessions.set(StrongerSession(currentUser.id))
                call.respond(response.status, ApiResponse.success(Token(jwtService.generateToken(currentUser))))
            }
            is Failed -> call.respond(response.status, ApiResponse.fail(response.message))
        }
    }
}