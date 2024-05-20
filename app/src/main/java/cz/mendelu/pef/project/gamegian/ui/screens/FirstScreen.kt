package cz.mendelu.pef.project.gamegian.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@Composable
fun FirstScreen(){
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
                TextField(value = "", onValueChange = {}, label = {
                    Text(text = "Username")
                })
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Male")
                        RadioButton(selected = true, onClick = { /*TODO*/ })
                    }
                    Spacer(modifier = Modifier.width(120.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Female")
                        RadioButton(selected = false, onClick = { /*TODO*/ })
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "join leaderboard \uD83D\uDE0A")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "or")
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /*TODO*/ },
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