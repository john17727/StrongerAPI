package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.id.IntIdTable

object Categories: IntIdTable() {
    val name = varchar("name", 25)
    val imageUrl = varchar("imageUrl", 250).nullable()
}