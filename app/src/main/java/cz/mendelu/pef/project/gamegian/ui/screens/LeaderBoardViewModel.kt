package cz.mendelu.pef.project.gamegian.ui.screens
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.project.gamegian.MyDataStore
import cz.mendelu.pef.project.gamegian.utils.LeaderBoardService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderBoardViewModel @Inject constructor(
    private val dataStore: MyDataStore
) : ViewModel() {
    private val leaderBoardService = LeaderBoardService()
    private val scores = mutableStateOf<List<Pair<String, Int>>>(emptyList())

    init {
        viewModelScope.launch {
            dataStore.watchTime().collect { time ->
                val username = dataStore.watchUsername().firstOrNull() ?: "unknown"
                if (time != null && username != "Guest") {
                    submitScore(username, time.toInt())
                }
            }
        }
    }

    fun fetchScores() {
        leaderBoardService.getLeaderboard { leaderboard ->
            scores.value = leaderboard.map { it.username to it.score }
        }
    }

    fun getScores(): State<List<Pair<String, Int>>> {
        return scores
    }

    fun submitScore(username: String, score: Int) {
        leaderBoardService.saveOrUpdateUserScore(username, score)
    }
}