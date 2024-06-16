package cz.mendelu.pef.project.gamegian.ui

import cz.mendelu.pef.project.gamegian.ui.screens.EditStudyScreen.EditStudyViewModel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import cz.mendelu.pef.project.gamegian.ui.screens.EditWalkViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditWalkScreen(navigationRouter: INavigationRouter, id: Long) {
    val viewModel = hiltViewModel<EditWalkViewModel>()
    var steps by remember { mutableStateOf(0) }


    // Load workout from ViewModel when screen is visited
    LaunchedEffect(key1 = id) {
        viewModel.loadWalk(id)
    }

    // Update UI state when ViewModel's currentWorkout changes
    LaunchedEffect(viewModel.currentWalk) {
        viewModel.currentWalk?.let {
            steps = it.steps
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Edit Study",
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            Text(
                text = "Walking",
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = steps.toString(),
                onValueChange = { steps = it.toInt() },
                label = { Text(text = "steps") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                Button(onClick = {
                    viewModel.updateWalk(
                        steps = steps
                    )
                    navigationRouter.navigateToListScreen()
                }) {
                    Text(text = "Edit")
                }

            }
        }
    }
}