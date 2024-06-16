package cz.mendelu.pef.project.gamegian.ui.screens

import androidx.compose.foundation.layout.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen() {
    val viewModel = hiltViewModel<AddScreenViewModel>()
    val (reps, setReps) = remember { mutableStateOf(viewModel.workouting.reps) }
    val (sets, setSets) = remember { mutableStateOf(viewModel.workouting.sets) }
    val activityOptions = listOf("biceps curls", "push-ups", "sit-ups")
    val (activityExpanded, setActivityExpanded) = remember { mutableStateOf(false) }
    val (selectedActivity, setSelectedActivity) = remember { mutableStateOf(activityOptions[0]) }

    var sliderHoursPosition by remember { mutableStateOf(0f) }
    var sliderMinsPosition by remember { mutableStateOf(0f) }
    var steps by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Add Time",
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
            Row(Modifier.fillMaxWidth()) {
                Column(Modifier.weight(1f)) {
                    Text(text = "Workouts:", fontSize = 16.sp, fontWeight = FontWeight.Bold)
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
                            viewModel.addWorkout()
                        }) {
                            Text(text = "+ Add")
                        }
                    }
                }
                Spacer(Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Reps", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Picker(value = reps.toFloat(), onValueChange = { setReps(it.toInt()) })
                }
                Spacer(Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Sets", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Picker(value = sets.toFloat(), onValueChange = { setSets(it.toInt()) })
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Studying",
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Hours")

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
                Text(text = "Mins")
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
                    viewModel.addStudying()
                }) {
                    Text(text = "+ Add")
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Walking",
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = steps,
                onValueChange = { steps = it },
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
                    viewModel.walking.steps = steps.toInt()
                    viewModel.addWalking()
                }) {
                    Text(text = "+ Add")
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
