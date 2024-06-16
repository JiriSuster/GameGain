package cz.mendelu.pef.project.gamegian.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cz.mendelu.pef.project.gamegian.model.Study
import cz.mendelu.pef.project.gamegian.model.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workout")
    fun getAll(): Flow<List<Workout>>

    @Insert
    suspend fun insert(workout: Workout): Long

    @Delete
    suspend fun delete(workout: Workout)

}