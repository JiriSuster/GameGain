package cz.mendelu.pef.project.gamegian.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cz.mendelu.pef.project.gamegian.model.Workout
import kotlinx.coroutines.flow.Flow

interface ILocalWorkoutRepository {
    fun getAll(): Flow<List<Workout>>

    suspend fun insert(workout: Workout): Long

    suspend fun delete(workout: Workout)

    suspend fun update(workout: Workout)

    suspend fun getWorkout(id: Long): Workout
}