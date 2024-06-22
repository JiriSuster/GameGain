package cz.mendelu.pef.project.gamegian.ui.screens.EditWalkScreen

import cz.mendelu.pef.project.gamegian.database.ILocalWalkRepository
import cz.mendelu.pef.project.gamegian.model.Walk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.project.gamegian.MyDataStore
import cz.mendelu.pef.project.gamegian.utils.calculateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditWalkViewModel @Inject constructor(
    private val walkRepository: ILocalWalkRepository,
    private val myDataStore: MyDataStore
) : ViewModel() {

    var currentWalkId: Long = -1
    var currentWalk: Walk? by mutableStateOf(null)

    fun loadWalk(id: Long) {
        viewModelScope.launch {
            currentWalk = walkRepository.getWalk(id)
            currentWalkId = id
        }
    }

    fun updateWalk(steps: Int) {

        currentWalk?.let { walk ->
            val oldTime = calculateTime(walkingSteps= currentWalk!!.steps)
            walk.steps = steps
            walk.time = calculateTime(walkingSteps = steps)
            viewModelScope.launch {
                myDataStore.appendTime(walk.time - oldTime)
                walkRepository.update(walk)
            }
        }
    }
}