package cz.mendelu.pef.project.gamegian.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.project.gamegian.database.ILocalStudyRepository
import cz.mendelu.pef.project.gamegian.database.ILocalWalkRepository
import cz.mendelu.pef.project.gamegian.database.ILocalWorkoutRepository
import cz.mendelu.pef.project.gamegian.model.Study
import cz.mendelu.pef.project.gamegian.model.Walk
import cz.mendelu.pef.project.gamegian.model.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val studyRepository: ILocalStudyRepository,
    private val workoutRepository: ILocalWorkoutRepository,
    private val walkingRepository: ILocalWalkRepository
) : ViewModel() {

    private val _studies = MutableStateFlow<List<Study>>(emptyList())
    val studies: StateFlow<List<Study>> = _studies

    private val _workouts = MutableStateFlow<List<Workout>>(emptyList())
    val workouts: StateFlow<List<Workout>> = _workouts

    private val _walks = MutableStateFlow<List<Walk>>(emptyList())
    val walks: StateFlow<List<Walk>> = _walks

    fun loadAll() {
        viewModelScope.launch {
            studyRepository.getAll().collect { studiesList ->
                _studies.value = studiesList
            }
        }

        viewModelScope.launch {
            walkingRepository.getAll().collect { walksList ->
                _walks.value = walksList
            }
        }

        viewModelScope.launch {
            workoutRepository.getAll().collect { workoutsList ->
                _workouts.value = workoutsList
            }
        }
    }
}
