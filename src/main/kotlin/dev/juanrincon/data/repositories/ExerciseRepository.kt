package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.Categories
import dev.juanrincon.domain.daos.Exercises
import dev.juanrincon.domain.daos.Muscles
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.Exercise
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ExerciseRepository : Repository<Exercise> {
    override fun getById(id: Int) = transaction {
        Exercises
            .innerJoin(Muscles)
            .innerJoin(Categories)
            .slice(
                Exercises.id,
                Exercises.name,
                Exercises.imageUrl,
                Exercises.videoUrl,
                Muscles.name,
                Categories.name
            )
            .select { Exercises.id eq id }
            .map {
                Exercise(
                    it[Exercises.id].value,
                    it[Exercises.name],
                    it[Exercises.imageUrl],
                    it[Exercises.videoUrl],
                    null,
                    it[Muscles.name],
                    it[Categories.name]
                )
            }.first()
    }

    override fun getAll() = transaction {
        Exercises
            .innerJoin(Muscles)
            .innerJoin(Categories)
            .slice(
                Exercises.id,
                Exercises.name,
                Exercises.imageUrl,
                Exercises.videoUrl,
                Muscles.name,
                Categories.name
            )
            .selectAll()
            .map {
                Exercise(
                    it[Exercises.id].value,
                    it[Exercises.name],
                    it[Exercises.imageUrl],
                    it[Exercises.videoUrl],
                    null,
                    it[Muscles.name],
                    it[Categories.name]
                )
            }
    }

    override fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun add(entry: Exercise): Exercise {
        TODO("Not yet implemented")
    }

    override fun update(entry: Exercise): Exercise {
        TODO("Not yet implemented")
    }
}