package app.tracktion.domain.daos

import app.tracktion.domain.interfaces.Mapper
import app.tracktion.domain.models.Split
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Splits : IntIdTable() {
    val name = varchar("name", 25)
    val imageUrl = varchar("imageUrl", 250).nullable()
}

class SplitDAO(id: EntityID<Int>): IntEntity(id), Mapper<Split> {
    companion object : IntEntityClass<SplitDAO>(Splits)
    var name by Equipment.name
    var imageUrl by Equipment.imageUrl

    override fun toModel() = Split(id.value, name, imageUrl)
}