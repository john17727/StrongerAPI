package app.tracktion.data.repositories

import app.tracktion.domain.daos.EquipmentDAO
import app.tracktion.domain.interfaces.TypeRepository
import app.tracktion.domain.models.Equipment
import app.tracktion.plugins.dbQuery
import app.tracktion.domain.daos.Equipment as EquipmentEntity

class EquipmentRepository(private val host: String) : TypeRepository<Equipment> {
    override suspend fun getExercisesFor(name: String, limit: Int, offset: Long) = dbQuery {
        EquipmentDAO.find { EquipmentEntity.name eq name }.first()
            .exercises.limit(limit, offset).map { it.toModel(host) }
    }

    override suspend fun getExerciseCountFor(name: String) = dbQuery {
        EquipmentDAO.find { EquipmentEntity.name eq name }.first()
            .exercises.count()
    }

    override suspend fun findById(id: Int) = dbQuery {
        getDAOById(id)?.toModel()
    }

    override suspend fun getAll() = dbQuery {
        EquipmentDAO.all().map { it.toModel() }
    }

    override suspend fun getAllPaginated(limit: Int, offset: Long) = getAll()

    override suspend fun getCount() = dbQuery {
        EquipmentDAO.count()
    }

    private fun getDAOById(id: Int) = EquipmentDAO.findById(id)
}