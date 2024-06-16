package cz.mendelu.pef.project.gamegian.database

import cz.mendelu.pef.project.gamegian.model.Study
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalStudyRepository @Inject constructor(private val dao: StudyDao) : ILocalStudyRepository {

    override fun getAll(): Flow<List<Study>> {
        return dao.getAll()
    }

    override suspend fun insert(study: Study): Long {
        return dao.insert(study)
    }

    override suspend fun delete(study: Study) {
        dao.delete(study)
    }

    override suspend fun update(study: Study) {
        dao.update(study)
    }

    override suspend fun getStudy(id: Long): Study {
        return dao.getStudy(id)
    }
}