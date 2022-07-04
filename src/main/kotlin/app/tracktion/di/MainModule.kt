package app.tracktion.di

import app.tracktion.data.repositories.*
import app.tracktion.data.services.*
import app.tracktion.domain.interfaces.Repository
import app.tracktion.domain.models.User
import io.ktor.server.application.*

object MainModule {

    fun getExerciseService(host: String) = ExerciseService(ExerciseRepository(host))
    fun getCategoryService() = CategoryService(CategoryRepository())
    fun getMuscleService() = MuscleService(MuscleRepository())
    fun getEquipmentService() = EquipmentService(EquipmentRepository())
    fun getSplitService() = SplitService(SplitRepository())
    fun getUserService(userRepository: Repository<User>, environment: ApplicationEnvironment) =
        UserService(userRepository, environment)
}