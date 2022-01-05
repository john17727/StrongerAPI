package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable

object Instructions: IntIdTable() {
    val exerciseId = integer("exerciseId").references(Exercises.id)
    val step = integer("step")
    val text = varchar("text", 500)
}