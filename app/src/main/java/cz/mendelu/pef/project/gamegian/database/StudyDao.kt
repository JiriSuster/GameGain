package cz.mendelu.pef.project.gamegian.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cz.mendelu.pef.project.gamegian.model.Study
import cz.mendelu.pef.project.gamegian.model.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyDao {

    @Query("SELECT * FROM study")
    fun getAll(): Flow<List<Study>>

    @Insert
    suspend fun insert(study: Study): Long

    @Delete
    suspend fun delete(study: Study)

    @Update
    suspend fun update(study: Study)

    @Query("SELECT * from study WHERE id = :id")
    suspend fun getStudy(id: Long): Study

}