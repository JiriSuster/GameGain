package cz.mendelu.pef.project.gamegian.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddScreenViewModel @Inject constructor(
    //private val repository: ActivityRepository
) : ViewModel() {

    fun addWorkout(activity: String, reps: Int, sets: Int) {
        val time = calculateTime(workoutReps = reps, workoutSets = sets)
        viewModelScope.launch {
            //repository.addWorkout(activity, reps, sets, time)
        }
    }

    fun addStudying(hours: Int, minutes: Int) {
        val time = calculateTime(studyHours = hours, studyMinutes = minutes)
        viewModelScope.launch {
            //repository.addStudying(hours, minutes, time)
        }
    }

    fun addWalking(distance: Float) {
        val time = calculateTime(walkingSteps = distance.toInt())
        viewModelScope.launch {
            //repository.addWalking(distance, time)
        }
    }

    fun calculateTime(
        walkingSteps: Int = 0,
        workoutSets: Int = 0,
        workoutReps: Int = 0,
        studyHours: Int = 0,
        studyMinutes: Int = 0
    ): Int {
        val walkingReward = walkingSteps * 0.003 // 1 step = 0.003 minutes reward
        val workoutReward = 5 * workoutSets  +  2 * workoutReps
        val studyReward = (studyHours * 60 + studyMinutes) * 0.1667 // 1 hour = 10 minutes reward

        return (walkingReward + workoutReward + studyReward).toInt()
    }


}
