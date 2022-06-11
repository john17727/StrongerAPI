package app.tracktion.domain.daos

import app.tracktion.domain.interfaces.Mapper
import app.tracktion.domain.models.Category
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Categories: IntIdTable("category") {
    val name = varchar("name", 25)
    val imageUrl = varchar("imageUrl", 250).nullable()
}

class CategoryDAO(id: EntityID<Int>): IntEntity(id), Mapper<Category> {
    companion object : IntEntityClass<CategoryDAO>(Categories)
    var name by Categories.name
    var imageUrl by Categories.imageUrl

    override fun toModel() = Category(id.value, name, imageUrl)
}