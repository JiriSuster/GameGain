package cz.mendelu.pef.project.gamegian.database

import cz.mendelu.pef.project.gamegian.model.Walk
import cz.mendelu.pef.project.gamegian.model.Workout
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalWorkoutRepository @Inject constructor(private val dao: WorkoutDao) : ILocalWorkoutRepository {

    override fun getAll(): Flow<List<Workout>> {
        return dao.getAll()
    }

    override suspend fun insert(workout: Workout): Long {
        return dao.insert(workout)
    }

    override suspend fun delete(workout: Workout) {
        dao.delete(workout)
    }

    override suspend fun update(workout: Workout) {
        dao.update(workout)
    }

    override suspend fun getWorkout(id: Long): Workout {
        return dao.getWorkout(id)
    }
}