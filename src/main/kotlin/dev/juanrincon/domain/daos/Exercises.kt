package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable

object Exercises: IntIdTable() {
    val name = varchar("name", 50)
    val imageUrl = varchar("imageUrl", 250)
    val videoUrl = varchar("videoUrl", 250)
    val categoryId = integer("categoryId").references(Categories.id)
    val muscleId = integer("muscleId").references(Muscles.id)
}