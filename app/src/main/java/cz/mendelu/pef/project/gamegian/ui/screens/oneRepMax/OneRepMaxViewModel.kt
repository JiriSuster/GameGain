package cz.mendelu.pef.project.gamegian.ui.screens.oneRepMax

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class OneRepMaxViewModel @Inject constructor() : ViewModel() {
    var weight by mutableFloatStateOf(0f)

    var reps by mutableIntStateOf(0)
    var oneRepMax by mutableFloatStateOf(0f)

    var percentages by mutableStateOf(emptyList<Pair<Float, Float>>())

    fun updateWeight(newWeight: Float) {
        weight = newWeight
    }

    fun updateReps(newReps: Int) {
        reps = newReps
    }

    fun calculateOneRepMax() {
        val w = weight
        val r = reps
        val max = if (r == 0) 0f else w / (1.0278f - 0.0278f * r)
        oneRepMax = max

        percentages = (5..100 step 5).map { percentage ->
            val percentageValue = percentage / 100f
            Pair(percentageValue, max * percentageValue)
        }
    }
}
