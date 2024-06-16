package cz.mendelu.pef.project.gamegian.ui.screens.EditStudyScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.project.gamegian.MyDataStore
import cz.mendelu.pef.project.gamegian.database.LocalStudyRepository
import cz.mendelu.pef.project.gamegian.model.Study
import cz.mendelu.pef.project.gamegian.utils.calculateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditStudyViewModel @Inject constructor(
    private val studyRepository: LocalStudyRepository,
    private val myDataStore: MyDataStore
) : ViewModel() {

    var currentStudyId: Long = -1
    var currentStudy: Study? by mutableStateOf(null)

    fun loadStudy(id: Long) {
        viewModelScope.launch {
            currentStudy = studyRepository.getStudy(id)
            currentStudyId = id
        }
    }

    fun updateStudy(studyHours: Int, studyMins: Int) {
        currentStudy?.let { study ->
            // Calculate old time before update
            val oldTime = calculateTime(studyHours = study.studyHours, studyMinutes = study.studyMinutes)

            // Update study with new data
            study.studyHours = studyHours
            study.studyMinutes = studyMins
            study.time = calculateTime(studyHours = studyHours, studyMinutes = studyMins)

            viewModelScope.launch {
                // Subtract old time and add new time to MyDataStore
                myDataStore.appendTime(study.time - oldTime)
                studyRepository.update(study)
            }
        }
    }
}
