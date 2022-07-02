package app.tracktion.domain.models.routing

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/exercises")
class Exercises {
    @Serializable
    @Resource("count")
    class Count(val parent: Exercises = Exercises())

    @Serializable
    @Resource("{id}")
    class Id(val parent: Exercises = Exercises(), val id: Int)
}