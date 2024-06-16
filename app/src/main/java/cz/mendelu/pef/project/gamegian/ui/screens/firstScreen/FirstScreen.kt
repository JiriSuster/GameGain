package cz.mendelu.pef.project.gamegian.ui.screens.firstScreen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.project.gamegian.MyDataStore
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun FirstScreen(navigationRouter: INavigationRouter) {
    val context = LocalContext.current
    val myDataStore = MyDataStore(context)

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
                    runBlocking {
                        launch(Dispatchers.IO) {
                            myDataStore.updateUsername(usernameState.value)
                            myDataStore.updateGender(isMaleState.value)
                        }
                    }
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
