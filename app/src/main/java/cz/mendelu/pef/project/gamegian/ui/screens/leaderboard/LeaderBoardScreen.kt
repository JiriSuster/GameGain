package cz.mendelu.pef.project.gamegian.ui.screens.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cz.mendelu.pef.project.gamegian.R
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import cz.mendelu.pef.project.gamegian.toReadableTime
import cz.mendelu.pef.project.gamegian.ui.components.BottomNavigationBar
import cz.mendelu.pef.project.gamegian.ui.components.bottomNavItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderBoardScreen(navigationRouter: INavigationRouter) {
    val viewModel = hiltViewModel<LeaderBoardViewModel>()

    viewModel.fetchScores()

    val scoresState = viewModel.getScores()
    val scores = scoresState.value
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navigationRouter = navigationRouter, items = bottomNavItems)
        },
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navigationRouter.navigateToHomeScreen()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.leaderboard_title),
                        fontSize = 14.sp
                    )
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(scores) { index, (username, score) ->
                PlayerItem(index = index, username = username, score = score)
            }
        }
    }
}

@Composable
fun PlayerItem(index: Int, username: String, score: Int) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = Color(0xFFF4EDFF))
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically // Align items vertically centered
                ) {
                    Text(
                        text = (index + 1).toString(),
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.weight(1f)) // Pushes the username to the center
                    Text(
                        text = username,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.weight(1f)) // Balances the space on the right
                }
                Spacer(modifier = Modifier.height(8.dp)) // Add some space between the row and the score
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = score.toLong().toReadableTime(), fontSize = 18.sp)
                }
            }
        }
    }
}
