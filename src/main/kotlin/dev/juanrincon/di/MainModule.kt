package dev.juanrincon.di

import dev.juanrincon.data.repositories.CategoryRepository
import dev.juanrincon.data.repositories.MuscleRepository
import dev.juanrincon.data.services.CategoryService
import dev.juanrincon.data.services.MuscleService

object MainModule {

    fun getCategoryService() = CategoryService(CategoryRepository())
    fun getMuscleService() = MuscleService(MuscleRepository())
}