package cz.mendelu.pef.project.gamegian.ui.screens.addScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.project.gamegian.database.LocalStudyRepository
import cz.mendelu.pef.project.gamegian.database.LocalWalkRepository
import cz.mendelu.pef.project.gamegian.database.LocalWorkoutRepository
import cz.mendelu.pef.project.gamegian.model.Study
import cz.mendelu.pef.project.gamegian.model.Walk
import cz.mendelu.pef.project.gamegian.model.Workout
import cz.mendelu.pef.project.gamegian.utils.calculateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddScreenViewModel @Inject constructor(
    private val walkRepository: LocalWalkRepository,
    private val studyRepository: LocalStudyRepository,
    private val workoutRepository: LocalWorkoutRepository
) : ViewModel() {

    var walking: Walk = Walk(0)
    var studying: Study = Study(0)
    var workouting: Workout = Workout(0)

    fun addWorkout() {
        val time = calculateTime(workoutReps = workouting.reps, workoutSets =  workouting.sets)
        workouting.time = time
        viewModelScope.launch {
            workoutRepository.insert(workouting)
        }
    }

    fun addStudying() {
        val time = calculateTime(studyMinutes = studying.studyMinutes, studyHours = studying.studyHours)
        studying.time = time
        viewModelScope.launch {
            studyRepository.insert(studying)
        }
    }

    fun addWalking() {
        val time = calculateTime(walkingSteps = walking.steps)
        walking.time = time
        viewModelScope.launch {
            walkRepository.insert(walking)
        }
    }





}
