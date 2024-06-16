package cz.mendelu.pef.project.gamegian.ui.screens.EditStudyScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.project.gamegian.database.LocalStudyRepository
import cz.mendelu.pef.project.gamegian.model.Study
import cz.mendelu.pef.project.gamegian.utils.calculateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditStudyViewModel @Inject constructor(
    private val studyRepository: LocalStudyRepository
) : ViewModel() {

    var currentStudyId: Long = -1
    var currentStudy: Study? by mutableStateOf(null)

    fun loadStudy(id: Long) {
        viewModelScope.launch {
            currentStudy = studyRepository.getStudy(id)
            currentStudyId = id
        }
    }

    fun updateStudy(studyHours: Int, studyMins : Int) {
        currentStudy?.let { study ->
            study.studyMinutes = studyMins
            study.studyHours = studyHours
            study.time = calculateTime(studyHours = studyHours, studyMinutes = studyMins)
            viewModelScope.launch {
                studyRepository.update(study)
            }
        }
    }
}