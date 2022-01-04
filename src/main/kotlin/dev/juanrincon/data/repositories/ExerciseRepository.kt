package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.Categories
import dev.juanrincon.domain.daos.Exercises
import dev.juanrincon.domain.daos.Muscles
import dev.juanrincon.domain.interfaces.Repository
import dev.juanrincon.domain.models.Exercise
import org.jetbrains.exposed.sql.*
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

    override fun delete(id: Int) = transaction {
        0 < Exercises.deleteWhere { Exercises.id eq id }
    }

    override fun add(entry: Exercise) = transaction {
        val category = Categories.slice(Categories.id).select { Categories.name eq entry.category }
        val muscle = Muscles.slice(Muscles.id).select { Muscles.name eq entry.muscle }

        val id = Exercises.insertAndGetId {
            it[name] = entry.name
            it[imageUrl] = entry.imageUrl
            it[videoUrl] = entry.videoUrl
            it[categoryId] = category
            it[muscleId] = muscle
        }
        getById(id.value)
    }

    override fun update(entry: Exercise) = transaction {
        val category = Categories.slice(Categories.id).select { Categories.name eq entry.category }
        val muscle = Muscles.slice(Muscles.id).select { Muscles.name eq entry.muscle }

        Exercises.update({ Exercises.id eq entry.id }) {
            it[name] = entry.name
            it[imageUrl] = entry.imageUrl
            it[videoUrl] = entry.videoUrl
            it[categoryId] = category
            it[muscleId] = muscle
        }

        entry.id?.let {
            getById(it)
        }?: entry
    }
}