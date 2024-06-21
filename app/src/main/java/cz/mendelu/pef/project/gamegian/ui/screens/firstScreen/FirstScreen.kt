package cz.mendelu.pef.project.gamegian.ui.screens.firstScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import cz.mendelu.pef.project.gamegian.R

@Composable
fun FirstScreen(
    navigationRouter: INavigationRouter,
    viewModel: FirstScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val resources = context.resources

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
                Text(
                    text = resources.getString(R.string.join_leaderboard_title),
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = resources.getString(R.string.join_leaderboard_description)
                )
                Spacer(modifier = Modifier.height(64.dp))
                TextField(
                    value = usernameState.value,
                    onValueChange = { usernameState.value = it },
                    label = { Text(text = resources.getString(R.string.username_hint)) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = resources.getString(R.string.male_label))
                        RadioButton(
                            selected = isMaleState.value,
                            onClick = { isMaleState.value = true }
                        )
                    }
                    Spacer(modifier = Modifier.width(120.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = resources.getString(R.string.female_label))
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
                    Text(text = resources.getString(R.string.join_button_text))
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
                    Text(text = resources.getString(R.string.proceed_button_text))
                }
            }
        }
    }
}
