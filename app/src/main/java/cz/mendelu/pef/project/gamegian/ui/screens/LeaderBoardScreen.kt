import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter

// Composable element for displaying a player with their score
@Composable
fun PlayerItem(username: String, score: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color(0xFFF4EDFF)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Username: $username")
            Text(text = "Score: $score")
        }
    }
}

// Screen composable displaying leaderboard
@Composable
fun LeaderBoardScreen(navigationRouter: INavigationRouter) {
    val viewModel: LeaderBoardViewModel = viewModel()

    viewModel.fetchScores()

    val scoresState = viewModel.getScores()
    val scores = scoresState.value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(scores) { (username, score) ->
            PlayerItem(username = username, score = score)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
