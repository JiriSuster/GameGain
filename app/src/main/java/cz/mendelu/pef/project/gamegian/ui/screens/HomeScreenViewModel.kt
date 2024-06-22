package cz.mendelu.pef.project.gamegian.ui.screens

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.project.gamegian.MyDataStore
import cz.mendelu.pef.project.gamegian.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val myDataStore: MyDataStore,
) : ViewModel() {

    var isTimerRunning by mutableStateOf(false)
    var remainingTime by mutableStateOf(0L)
    private var context: Context? = null

    private var mediaPlayer: MediaPlayer? = null

    fun setContext(ctx: Context) {
        context = ctx
    }

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
                playTimerEndSound()
            }
        }
    }

    fun stopTimer() {
        isTimerRunning = false
        viewModelScope.launch {
            myDataStore.updateTime(remainingTime)
        }
    }

    private fun playTimerEndSound() {
        mediaPlayer = MediaPlayer.create(context, R.raw.timer_end_sound)
        mediaPlayer?.setOnCompletionListener {
            it.release()
        }
        mediaPlayer?.start()
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
