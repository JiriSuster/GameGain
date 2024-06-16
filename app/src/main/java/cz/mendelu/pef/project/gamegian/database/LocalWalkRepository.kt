package cz.mendelu.pef.project.gamegian.database

import cz.mendelu.pef.project.gamegian.model.Walk
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalWalkRepository @Inject constructor(private val dao: WalkDao) : ILocalWalkRepository {

    override fun getAll(): Flow<List<Walk>> {
        return dao.getAll()
    }

    override suspend fun insert(walk: Walk): Long {
        return dao.insert(walk)
    }

    override suspend fun delete(walk: Walk) {
        dao.delete(walk)
    }
}