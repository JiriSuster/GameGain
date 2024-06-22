package cz.mendelu.pef.project.gamegian.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cz.mendelu.pef.project.gamegian.MyDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val myDataStore: MyDataStore
) : ViewModel() {

    var isTimerRunning by mutableStateOf(false)

    var remainingTime by mutableStateOf(0L)

    init {
        viewModelScope.launch {
            remainingTime = myDataStore.watchTime().first() ?: 0L
        }
    }

    fun toggleTimer() {
        if (isTimerRunning) {
            stopTimer()
        } else {
            startTimer()
        }
    }

    private fun startTimer() {
        isTimerRunning = true
        viewModelScope.launch {
            while (isTimerRunning && remainingTime > 0) {
                delay(1000L)
                remainingTime -= 1
            }
            if (remainingTime <= 0) {
                stopTimer()
            }
        }
    }

    fun stopTimer() {
        isTimerRunning = false
        viewModelScope.launch {
            myDataStore.updateTime(remainingTime)
        }
    }
}
