package cz.mendelu.pef.project.gamegian.database


import cz.mendelu.pef.project.gamegian.model.Study
import kotlinx.coroutines.flow.Flow

interface ILocalStudyRepository {

    fun getAll(): Flow<List<Study>>

    suspend fun insert(study: Study): Long

    suspend fun delete(study: Study)
    suspend fun update(study: Study)
    suspend fun getStudy(id: Long): Study
}