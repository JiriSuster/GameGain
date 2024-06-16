package cz.mendelu.pef.project.gamegian.ui.screens.editWorkout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.project.gamegian.database.LocalWorkoutRepository
import cz.mendelu.pef.project.gamegian.model.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditWorkoutsViewModel @Inject constructor(
    private val workoutRepository: LocalWorkoutRepository
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
            workout.exercise_name = exerciseName
            workout.reps = reps
            workout.sets = sets
            viewModelScope.launch {
                workoutRepository.update(workout)
            }
        }
    }
}