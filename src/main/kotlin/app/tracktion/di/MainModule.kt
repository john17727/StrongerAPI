package app.tracktion.di

import app.tracktion.data.repositories.CategoryRepository
import app.tracktion.data.repositories.ExerciseRepository
import app.tracktion.data.repositories.MuscleRepository
import app.tracktion.data.services.CategoryService
import app.tracktion.data.services.ExerciseService
import app.tracktion.data.services.MuscleService
import app.tracktion.data.services.UserService
import app.tracktion.domain.interfaces.Repository
import app.tracktion.domain.models.User
import io.ktor.server.application.*

object MainModule {

    fun getExerciseService() = ExerciseService(ExerciseRepository())
    fun getCategoryService() = CategoryService(CategoryRepository())
    fun getMuscleService() = MuscleService(MuscleRepository())
    fun getUserService(userRepository: Repository<User>, environment: ApplicationEnvironment) =
        UserService(userRepository, environment)
}