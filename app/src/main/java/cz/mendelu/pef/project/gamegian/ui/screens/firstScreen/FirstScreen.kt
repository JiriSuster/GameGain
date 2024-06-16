package cz.mendelu.pef.project.gamegian.ui.screens.firstScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.project.gamegian.MyDataStore
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import kotlinx.coroutines.flow.first
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FirstScreen(
    navigationRouter: INavigationRouter,
    viewModel: FirstScreenViewModel = hiltViewModel()
) {
    val myDataStore = viewModel.myDataStore

    val usernameState = remember { mutableStateOf("") }
    val isMaleState = remember { mutableStateOf(true) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Join the Leaderboard!", style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Help us make your experience even better by providing some details. This will allow you to join our leaderboard and get more accurate calculations.")
                Spacer(modifier = Modifier.height(64.dp))
                TextField(
                    value = usernameState.value,
                    onValueChange = { usernameState.value = it },
                    label = { Text(text = "Username") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Male")
                        RadioButton(
                            selected = isMaleState.value,
                            onClick = { isMaleState.value = true }
                        )
                    }
                    Spacer(modifier = Modifier.width(120.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Female")
                        RadioButton(
                            selected = !isMaleState.value,
                            onClick = { isMaleState.value = false }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    viewModel.updateUsername(usernameState.value)
                    viewModel.updateGender(isMaleState.value)
                    navigationRouter.navigateToHomeScreen()
                }) {
                    Text(text = "join leaderboard \uD83D\uDE0A")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "or")
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navigationRouter.navigateToHomeScreen() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.DarkGray
                    )
                ) {
                    Text(text = "proceed without joining \uD83D\uDE1E")
                }
            }
        }
    }
}
