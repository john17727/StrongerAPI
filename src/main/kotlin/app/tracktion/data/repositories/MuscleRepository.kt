package app.tracktion.data.repositories

import app.tracktion.domain.daos.MuscleDAO
import app.tracktion.domain.daos.Muscles
import app.tracktion.domain.interfaces.TypeRepository
import app.tracktion.domain.models.Muscle
import app.tracktion.plugins.dbQuery

class MuscleRepository(private val host: String) : TypeRepository<Muscle> {
    override suspend fun getExercisesFor(name: String, limit: Int, offset: Long) = dbQuery {
        MuscleDAO.find { Muscles.name eq name }.first()
            .exercises.limit(limit, offset).map { it.toModel(host) }
    }

    override suspend fun getExerciseCountFor(name: String) = dbQuery {
        MuscleDAO.find { Muscles.name eq name }.first()
            .exercises.count()
    }
    override suspend fun findById(id: Int) = dbQuery {
        getDAOById(id)?.toModel()
    }

    override suspend fun getAll() = dbQuery {
        MuscleDAO.all().map { it.toModel() }
    }

    override suspend fun getAllPaginated(limit: Int, offset: Long) = getAll()

    override suspend fun getCount() = dbQuery {
        MuscleDAO.count()
    }

    private fun getDAOById(id: Int) = MuscleDAO.findById(id)
}