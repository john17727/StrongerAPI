package app.tracktion.data.repositories

import app.tracktion.domain.daos.EquipmentDAO
import app.tracktion.domain.interfaces.ReadRepository
import app.tracktion.domain.models.Equipment
import app.tracktion.plugins.dbQuery

class EquipmentRepository : ReadRepository<Equipment> {
    override suspend fun findById(id: Int) = dbQuery {
        getDAOById(id)?.toModel()
    }

    override suspend fun getAll() = dbQuery {
        EquipmentDAO.all().map { it.toModel() }
    }

    private fun getDAOById(id: Int) = EquipmentDAO.findById(id)
}