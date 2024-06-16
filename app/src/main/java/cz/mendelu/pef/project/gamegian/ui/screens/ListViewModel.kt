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
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val studyRepository: ILocalStudyRepository,
    private val workoutRepository: ILocalWorkoutRepository,
    private val walkingRepository: ILocalWalkRepository
) : ViewModel() {

    private val _combined = MutableStateFlow<List<Any>>(emptyList())
    val combined: StateFlow<List<Any>> = _combined

    fun loadAll() {
        viewModelScope.launch {
            val studiesList = studyRepository.getAll().firstOrNull()?.sortedByDescending { it.date } ?: emptyList()
            val walksList = walkingRepository.getAll().firstOrNull()?.sortedByDescending { it.date } ?: emptyList()
            val workoutsList = workoutRepository.getAll().firstOrNull()?.sortedByDescending { it.date } ?: emptyList()

            val combinedList = (studiesList + walksList + workoutsList).sortedByDescending { item ->
                when (item) {
                    is Study -> item.date
                    is Walk -> item.date
                    is Workout -> item.date
                    else -> throw IllegalArgumentException("Unknown type")
                }
            }

            _combined.value = combinedList
        }
    }

    fun deleteItem(item: Any) {
        viewModelScope.launch {
            when (item) {
                is Study -> {
                    studyRepository.delete(item)
                }
                is Walk -> {
                    walkingRepository.delete(item)
                }
                is Workout -> {
                    workoutRepository.delete(item)
                }
                else -> throw IllegalArgumentException("Unknown item type")
            }
            loadAll()
        }
    }
}
