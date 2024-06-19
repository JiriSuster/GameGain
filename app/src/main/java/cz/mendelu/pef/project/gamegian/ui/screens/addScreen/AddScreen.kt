package cz.mendelu.pef.project.gamegian.ui.screens.addScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chillibits.composenumberpicker.VerticalNumberPicker
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chillibits.composenumberpicker.VerticalNumberPicker
import cz.mendelu.pef.project.gamegian.R
import cz.mendelu.pef.project.gamegian.navigation.bottomNavItems
import cz.mendelu.pef.project.gamegian.ui.components.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navigationRouter: INavigationRouter) {
    val viewModel = hiltViewModel<AddScreenViewModel>()
    val (reps, setReps) = remember { mutableStateOf(viewModel.workouting.reps) }
    val (sets, setSets) = remember { mutableStateOf(viewModel.workouting.sets) }
    val activityOptions = listOf(
        stringResource(R.string.activity_biceps_curls),
        stringResource(R.string.activity_push_ups),
        stringResource(R.string.activity_sit_ups),
        stringResource(R.string.activity_pull_ups),
        stringResource(R.string.activity_dips)
    )
    val (activityExpanded, setActivityExpanded) = remember { mutableStateOf(false) }
    val (selectedActivity, setSelectedActivity) = remember { mutableStateOf(activityOptions[0]) }

    var sliderHoursPosition by remember { mutableStateOf(0f) }
    var sliderMinsPosition by remember { mutableStateOf(0f) }
    var steps by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navigationRouter = navigationRouter, items = bottomNavItems)
        },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navigationRouter.returnBack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back_button_content_description))
                    }
                },
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.add_time_title),
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 14.sp
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
            Row(Modifier.fillMaxWidth()) {
                Column(Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.workouts_label), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    ExposedDropdownMenuBox(
                        expanded = activityExpanded,
                        onExpandedChange = setActivityExpanded
                    ) {
                        TextField(
                            modifier = Modifier.menuAnchor(),
                            value = selectedActivity,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = activityExpanded) }
                        )
                        ExposedDropdownMenu(
                            expanded = activityExpanded,
                            onDismissRequest = { setActivityExpanded(!activityExpanded) }) {
                            activityOptions.forEachIndexed { index, activity ->
                                DropdownMenuItem(
                                    text = { Text(text = activity) },
                                    onClick = {
                                        setSelectedActivity(activityOptions[index])
                                        setActivityExpanded(false)
                                    },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                )
                            }
                        }
                    }
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(onClick = {
                            viewModel.workouting.exercise_name = selectedActivity
                            viewModel.workouting.reps = reps
                            viewModel.workouting.sets = sets
                            viewModel.workouting.date = System.currentTimeMillis()
                            viewModel.addWorkout()
                            navigationRouter.navigateToListScreen()
                        }) {
                            Text(text = stringResource(R.string.add_button_text))
                        }
                    }
                }
                Spacer(Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(R.string.reps_label), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Picker(value = reps.toFloat(), onValueChange = { setReps(it.toInt()) })
                }
                Spacer(Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(R.string.sets_label), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Picker(value = sets.toFloat(), onValueChange = { setSets(it.toInt()) })
                }
            }

            Spacer(Modifier.height(16.dp))

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
                    viewModel.studying.studyHours = sliderHoursPosition.toInt()
                    viewModel.studying.studyMinutes = sliderMinsPosition.toInt()
                    viewModel.studying.date = System.currentTimeMillis()
                    viewModel.addStudying()
                    navigationRouter.navigateToListScreen()
                }) {
                    Text(text = stringResource(R.string.add_button_text))
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.walking_label),
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = steps,
                onValueChange = { steps = it },
                label = { Text(text = stringResource(R.string.steps_label)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                Button(onClick = {
                    viewModel.walking.steps = steps.toInt()
                    viewModel.walking.date = System.currentTimeMillis()
                    viewModel.addWalking()
                    navigationRouter.navigateToListScreen()
                }) {
                    Text(text = stringResource(R.string.add_button_text))
                }
            }

        }
    }
}

@Composable
fun Picker(value: Float, onValueChange: (Float) -> Unit) {
    VerticalNumberPicker(
        min = 0,
        max = 300,
        default = value.toInt(),
        modifier = Modifier.padding(10.dp),
        onValueChange = { onValueChange(it.toFloat()) }
    )
}
