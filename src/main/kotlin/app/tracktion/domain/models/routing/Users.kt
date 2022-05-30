package app.tracktion.domain.models.routing

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/users")
class Users {
    @Serializable
    @Resource("login")
    class Login(val parent: Users = Users())

    @Serializable
    @Resource("create")
    class New(val parent: Users = Users())
}