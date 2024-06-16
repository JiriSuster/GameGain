package cz.mendelu.pef.project.gamegian.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cz.mendelu.pef.project.gamegian.model.Study
import cz.mendelu.pef.project.gamegian.model.Walk
import kotlinx.coroutines.flow.Flow

@Dao
interface WalkDao {

    @Query("SELECT * FROM walk")
    fun getAll(): Flow<List<Walk>>

    @Insert
    suspend fun insert(walk: Walk): Long

    @Delete
    suspend fun delete(walk: Walk)

}