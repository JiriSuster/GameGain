package cz.mendelu.pef.project.gamegian.ui.screens.editWorkout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.project.gamegian.MyDataStore
import cz.mendelu.pef.project.gamegian.database.LocalWorkoutRepository
import cz.mendelu.pef.project.gamegian.model.Workout
import cz.mendelu.pef.project.gamegian.utils.calculateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditWorkoutsViewModel @Inject constructor(
    private val workoutRepository: LocalWorkoutRepository,
    private val myDataStore: MyDataStore
) : ViewModel() {

    var currentWorkoutId: Long = -1
    var currentWorkout: Workout? by mutableStateOf(null)

    fun loadWorkout(id: Long) {
        viewModelScope.launch {
            currentWorkout = workoutRepository.getWorkout(id)
            currentWorkoutId = id
        }
    }

    fun updateWorkout(exerciseName: String, reps: Int, sets: Int) {
        currentWorkout?.let { workout ->
            val oldTime = calculateTime(workoutReps = currentWorkout!!.reps, workoutSets = currentWorkout!!.sets)
            workout.exercise_name = exerciseName
            workout.reps = reps
            workout.sets = sets
            workout.time = calculateTime(workoutReps = reps, workoutSets = sets)
            viewModelScope.launch {
                myDataStore.appendTime(workout.time - oldTime)
                workoutRepository.update(workout)
            }
        }
    }
}