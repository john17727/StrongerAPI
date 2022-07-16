package app.tracktion.di

import app.tracktion.data.repositories.*
import app.tracktion.data.services.*
import app.tracktion.domain.interfaces.Repository
import app.tracktion.domain.models.User
import io.ktor.server.application.*

object MainModule {

    fun getExerciseService(host: String) = ExerciseService(ExerciseRepository(host))
    fun getCategoryService(host: String) = CategoryService(CategoryRepository(host))
    fun getMuscleService(host: String) = MuscleService(MuscleRepository(host))
    fun getEquipmentService(host: String) = EquipmentService(EquipmentRepository(host))
    fun getSplitService(host: String) = SplitService(SplitRepository(host))
    fun getUserService(userRepository: Repository<User>, environment: ApplicationEnvironment) =
        UserService(userRepository, environment)
}