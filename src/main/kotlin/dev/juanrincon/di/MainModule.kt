package dev.juanrincon.di

import dev.juanrincon.data.repositories.CategoryRepository
import dev.juanrincon.data.repositories.MuscleRepository
import dev.juanrincon.data.services.CategoryService
import dev.juanrincon.data.services.MuscleService
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.Category
import dev.juanrincon.domain.models.Muscle
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module(createdAtStart = true) {

    single { CategoryService(get(named<Category>())) }

    single<Repository<Category>>(named<Category>()) { CategoryRepository() }

    single { MuscleService(get(named<Muscle>())) }

    single<Repository<Muscle>>(named<Muscle>()) { MuscleRepository() }
}