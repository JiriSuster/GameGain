package cz.mendelu.pef.project.gamegian.database


import cz.mendelu.pef.project.gamegian.model.Walk
import kotlinx.coroutines.flow.Flow

interface ILocalWalkRepository {
    fun getAll(): Flow<List<Walk>>

    suspend fun insert(walk: Walk): Long

    suspend fun delete(walk: Walk)
}