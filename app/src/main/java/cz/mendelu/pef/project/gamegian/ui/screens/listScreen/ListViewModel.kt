package cz.mendelu.pef.project.gamegian.ui.screens.listScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.project.gamegian.database.ILocalStudyRepository
import cz.mendelu.pef.project.gamegian.database.ILocalWalkRepository
import cz.mendelu.pef.project.gamegian.database.ILocalWorkoutRepository
import cz.mendelu.pef.project.gamegian.model.Study
import cz.mendelu.pef.project.gamegian.model.Walk
import cz.mendelu.pef.project.gamegian.model.Workout
import cz.mendelu.pef.project.gamegian.MyDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val studyRepository: ILocalStudyRepository,
    private val workoutRepository: ILocalWorkoutRepository,
    private val walkingRepository: ILocalWalkRepository,
    private val myDataStore: MyDataStore
) : ViewModel() {

    private val _combined = MutableStateFlow<List<Any>>(emptyList())
    val combined: StateFlow<List<Any>> = _combined

    fun loadAll() {
        viewModelScope.launch {
            val studies = studyRepository.getAll()
            val walks = walkingRepository.getAll()
            val workouts = workoutRepository.getAll()

            studies.collect { studiesList ->
                val sortedStudies = studiesList.sortedByDescending { it.date }
                walks.collect { walksList ->
                    val sortedWalks = walksList.sortedByDescending { it.date }
                    workouts.collect { workoutsList ->
                        val sortedWorkouts = workoutsList.sortedByDescending { it.date }

                        val combinedList = (sortedStudies + sortedWalks + sortedWorkouts).sortedByDescending { item ->
                            when (item) {
                                is Study -> item.date
                                is Walk -> item.date
                                is Workout -> item.date
                                else -> throw IllegalArgumentException("Unknown type")
                            }
                        }

                        _combined.value = combinedList

                        if (combinedList.isEmpty()) {
                            myDataStore.updateTime(0)
                        }
                    }
                }
            }
        }
    }

    fun deleteItem(item: Any) {
        viewModelScope.launch {
            when (item) {
                is Study -> {
                    val timeToSubtract = item.time ?: 0L
                    myDataStore.appendTime(-timeToSubtract)
                    studyRepository.delete(item)
                }
                is Walk -> {
                    val timeToSubtract = item.time ?: 0L
                    myDataStore.appendTime(-timeToSubtract)
                    walkingRepository.delete(item)
                }
                is Workout -> {
                    val timeToSubtract = item.time ?: 0L
                    myDataStore.appendTime(-timeToSubtract)
                    workoutRepository.delete(item)
                }
                else -> throw IllegalArgumentException("Unknown item type")
            }
            loadAll()
        }
    }
}
