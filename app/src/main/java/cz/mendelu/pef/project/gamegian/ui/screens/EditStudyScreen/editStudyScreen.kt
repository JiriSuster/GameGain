package cz.mendelu.pef.project.gamegian.ui.screens.EditStudyScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import cz.mendelu.pef.project.gamegian.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditStudyScreen(navigationRouter: INavigationRouter, id: Long) {
    val viewModel = hiltViewModel<EditStudyViewModel>()
    var sliderHoursPosition by remember { mutableStateOf(0f) }
    var sliderMinsPosition by remember { mutableStateOf(0f) }

    // Load workout from ViewModel when screen is visited
    LaunchedEffect(key1 = id) {
        viewModel.loadStudy(id)
    }

    // Update UI state when ViewModel's currentWorkout changes
    LaunchedEffect(viewModel.currentStudy) {
        viewModel.currentStudy?.let {
            sliderMinsPosition = it.studyMinutes.toFloat()
            sliderHoursPosition = it.studyHours.toFloat()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.edit_study_title),
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
                text = stringResource(R.string.studying_label),
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = stringResource(R.string.hours_label))

                Slider(
                    value = sliderHoursPosition,
                    onValueChange = { sliderHoursPosition = it },
                    valueRange = 0f..10f,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(1f)
                )

                Text(text = "${sliderHoursPosition.toInt()}")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = stringResource(R.string.minutes_label))
                Slider(
                    value = sliderMinsPosition,
                    onValueChange = { sliderMinsPosition = it },
                    valueRange = 0f..59f,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(1f)
                )
                Text(text = "${sliderMinsPosition.toInt()}")
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                Button(onClick = {
                    viewModel.updateStudy(
                        studyHours = sliderHoursPosition.toInt(),
                        studyMins = sliderMinsPosition.toInt()
                    )
                    navigationRouter.navigateToListScreen()
                }) {
                    Text(text = stringResource(R.string.edit_button_text))
                }
            }
        }
    }
}
