package app.tracktion.domain.daos

import app.tracktion.domain.interfaces.Mapper
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import app.tracktion.domain.models.Equipment as EquipmentModel

object Equipment : IntIdTable() {
    val name = varchar("name", 25)
    val imageUrl = varchar("imageUrl", 250).nullable()
}

class EquipmentDAO(id: EntityID<Int>): IntEntity(id), Mapper<EquipmentModel> {
    companion object : IntEntityClass<EquipmentDAO>(Equipment)
    var name by Equipment.name
    var imageUrl by Equipment.imageUrl

    override fun toModel() = EquipmentModel(id.value, name, imageUrl)
}