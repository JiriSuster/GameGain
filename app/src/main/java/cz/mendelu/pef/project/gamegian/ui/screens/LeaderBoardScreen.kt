import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import cz.mendelu.pef.project.gamegian.R

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
            Text(text = stringResource(id = R.string.username_label))
            Text(text = username)
            Text(text = stringResource(id = R.string.score_label))
            Text(text = score.toString())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderBoardScreen(navigationRouter: INavigationRouter) {
    val viewModel: LeaderBoardViewModel = viewModel()

    viewModel.fetchScores()

    val scoresState = viewModel.getScores()
    val scores = scoresState.value
    Scaffold(

        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navigationRouter.navigateToHomeScreen()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        androidx.compose.material3.Text(
                            text = stringResource(id = R.string.leaderboard_title),
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 14.sp
                        )
                    }
                }
            )
        }

    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(scores) { (username, score) ->
                PlayerItem(username = username, score = score)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}