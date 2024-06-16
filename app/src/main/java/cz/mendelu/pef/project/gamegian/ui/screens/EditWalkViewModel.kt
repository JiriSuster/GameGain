package cz.mendelu.pef.project.gamegian.ui.screens

import cz.mendelu.pef.project.gamegian.database.ILocalWalkRepository
import cz.mendelu.pef.project.gamegian.database.LocalWalkRepository
import cz.mendelu.pef.project.gamegian.model.Walk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.project.gamegian.model.Study
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditWalkViewModel @Inject constructor(
    private val walkRepository: ILocalWalkRepository
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
            walk.steps = steps
            viewModelScope.launch {
                walkRepository.update(walk)
            }
        }
    }
}