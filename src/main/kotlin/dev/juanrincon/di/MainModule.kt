package dev.juanrincon.di

import dev.juanrincon.data.repositories.CategoryRepository
import dev.juanrincon.data.repositories.ExerciseRepository
import dev.juanrincon.data.repositories.MuscleRepository
import dev.juanrincon.data.repositories.UserRepository
import dev.juanrincon.data.services.CategoryService
import dev.juanrincon.data.services.ExerciseService
import dev.juanrincon.data.services.MuscleService
import dev.juanrincon.data.services.UserService
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.User

object MainModule {

    fun getExerciseService() = ExerciseService(ExerciseRepository())
    fun getCategoryService() = CategoryService(CategoryRepository())
    fun getMuscleService() = MuscleService(MuscleRepository())
    fun getUserService(userRepository: Repository<User>) = UserService(userRepository)
}