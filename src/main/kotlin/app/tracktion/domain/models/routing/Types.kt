package app.tracktion.domain.models.routing

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/types")
class Types {
    @Serializable
    @Resource("categories")
    class Categories(val parent: Types = Types()) {
        @Serializable
        @Resource("{name}/exercises")
        class Exercises(val parent: Categories = Categories(), val name: String)
    }

    @Serializable
    @Resource("muscles")
    class Muscles(val parent: Types = Types())

    @Serializable
    @Resource("equipment")
    class Equipment(val parent: Types = Types())

    @Serializable
    @Resource("splits")
    class Splits(val parent: Types = Types())
}