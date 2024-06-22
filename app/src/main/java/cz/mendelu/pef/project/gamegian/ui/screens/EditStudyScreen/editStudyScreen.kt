package cz.mendelu.pef.project.gamegian.ui.screens.EditStudyScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import cz.mendelu.pef.project.gamegian.R
import cz.mendelu.pef.project.gamegian.ui.components.BottomNavigationBar
import cz.mendelu.pef.project.gamegian.ui.components.bottomNavItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditStudyScreen(navigationRouter: INavigationRouter, id: Long) {
    val viewModel = hiltViewModel<EditStudyViewModel>()
    var sliderHoursPosition by remember { mutableStateOf(0f) }
    var sliderMinsPosition by remember { mutableStateOf(0f) }

    val snackbarHostState = remember { SnackbarHostState() }
    val (showError, setShowError) = remember { mutableStateOf(false) }
    val errorMessage = stringResource(id = R.string.error_invalid_input)

    LaunchedEffect(key1 = id) {
        viewModel.loadStudy(id)
    }

    LaunchedEffect(viewModel.currentStudy) {
        viewModel.currentStudy?.let {
            sliderMinsPosition = it.studyMinutes.toFloat()
            sliderHoursPosition = it.studyHours.toFloat()
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navigationRouter = navigationRouter, items = bottomNavItems)
        },
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navigationRouter.returnBack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back_button_content_description))
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.edit_study_title),
                        fontSize = 14.sp
                    )
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
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
                    if(sliderHoursPosition.toInt() == 0 && sliderMinsPosition.toInt() == 0){
                        setShowError(true)
                    }
                    else {
                        viewModel.updateStudy(
                            studyHours = sliderHoursPosition.toInt(),
                            studyMins = sliderMinsPosition.toInt()
                        )
                        navigationRouter.navigateToListScreen()
                    }
                }) {
                    Text(text = stringResource(R.string.edit_button_text))
                }
            }
            if (showError) {
                LaunchedEffect(snackbarHostState) {
                    snackbarHostState.showSnackbar(
                        message = errorMessage,
                        duration = SnackbarDuration.Short
                    )
                    setShowError(false)
                }
            }
        }
    }
}
