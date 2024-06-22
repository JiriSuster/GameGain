package cz.mendelu.pef.project.gamegian.ui.screens.firstScreen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import androidx.hilt.navigation.compose.hiltViewModel
import cz.mendelu.pef.project.gamegian.MainActivity
import cz.mendelu.pef.project.gamegian.R

@Composable
fun FirstScreen(
    navigationRouter: INavigationRouter,
) {
    val viewModel = hiltViewModel<FirstScreenViewModel>()
    val context = LocalContext.current
    val mainActivity = context as MainActivity

    val usernameState = remember { mutableStateOf("") }
    val isMaleState = remember { mutableStateOf(true) }
    val errorState = remember { mutableStateOf("") }

    val usernameEmptyError = stringResource(R.string.username_empty_error)
    val usernameExistsError = stringResource(R.string.username_exists_error)


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
                    text = stringResource(R.string.join_leaderboard_title),
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.join_leaderboard_description)
                )
                Spacer(modifier = Modifier.height(64.dp))
                TextField(
                    value = usernameState.value,
                    onValueChange = { usernameState.value = it },
                    label = { Text(text = stringResource(R.string.username_hint)) }
                )
                if (errorState.value.isNotEmpty()) {
                    Text(
                        text = errorState.value,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = stringResource(R.string.male_label))
                        RadioButton(
                            selected = isMaleState.value,
                            onClick = { isMaleState.value = true }
                        )
                    }
                    Spacer(modifier = Modifier.width(120.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = stringResource(R.string.female_label))
                        RadioButton(
                            selected = !isMaleState.value,
                            onClick = { isMaleState.value = false }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    when {
                        usernameState.value.isEmpty() -> {
                            errorState.value = usernameEmptyError
                        }
                        else -> {
                            viewModel.checkUsernameExists(usernameState.value) { exists ->
                                if (exists) {
                                    errorState.value = usernameExistsError
                                } else {
                                    viewModel.updateUsername(usernameState.value)
                                    viewModel.updateGender(isMaleState.value)
                                    mainActivity.setFirstLaunchComplete()
                                    navigationRouter.navigateToHomeScreen()
                                }
                            }
                        }
                    }
                }) {
                    Text(text = stringResource(R.string.join_button_text))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = stringResource(R.string.or))
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        mainActivity.setFirstLaunchComplete()
                        navigationRouter.navigateToHomeScreen()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.DarkGray
                    )
                ) {
                    Text(text = stringResource(R.string.proceed_button_text))
                }
            }
        }
    }
}
