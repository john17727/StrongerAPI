package dev.juanrincon.domain.daos

import dev.juanrincon.domain.interfaces.Mapper
import dev.juanrincon.domain.models.Muscle
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Muscles: IntIdTable() {
    val name = varchar("name", 25)
    val imageUrl = varchar("imageUrl", 250).nullable()
}

class MuscleDAO(id: EntityID<Int>): IntEntity(id), Mapper<Muscle> {
    companion object : IntEntityClass<MuscleDAO>(Muscles)
    var name by Muscles.name
    var imageUrl by Muscles.imageUrl

    override fun toModel() = Muscle(id.value, name, imageUrl)
}