package cz.mendelu.pef.project.gamegian.ui.screens.editWorkout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import cz.mendelu.pef.project.gamegian.ui.screens.addScreen.Picker
import cz.mendelu.pef.project.gamegian.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditWorkout(navigationRouter: INavigationRouter, id: Long) {
    val viewModel = hiltViewModel<EditWorkoutsViewModel>()
    val activityOptions = listOf(
        "biceps curls", "push-ups", "sit-ups", "pull-ups", "dips"
    )
    var activityExpanded by remember { mutableStateOf(false) }
    var selectedActivity by remember { mutableStateOf<String?>(null) }
    var reps by remember { mutableStateOf<Int?>(null) }
    var sets by remember { mutableStateOf<Int?>(null) }

    // Load workout from ViewModel when screen is visited
    LaunchedEffect(key1 = id) {
        viewModel.loadWorkout(id)
    }

    // Update UI state when ViewModel's currentWorkout changes
    LaunchedEffect(viewModel.currentWorkout) {
        viewModel.currentWorkout?.let {
            selectedActivity = it.exercise_name
            reps = it.reps
            sets = it.sets
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(id = R.string.edit_workout_title),
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
                    Text(
                        text = stringResource(id = R.string.workouts_label),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    ExposedDropdownMenuBox(
                        expanded = activityExpanded,
                        onExpandedChange = { activityExpanded = it }
                    ) {
                        TextField(
                            modifier = Modifier.menuAnchor(),
                            value = selectedActivity ?: "",
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = activityExpanded
                                )
                            }
                        )
                        ExposedDropdownMenu(
                            expanded = activityExpanded,
                            onDismissRequest = { activityExpanded = false }
                        ) {
                            activityOptions.forEachIndexed { index, activity ->
                                DropdownMenuItem(
                                    text = { Text(text = activity) },
                                    onClick = {
                                        selectedActivity = activity
                                        activityExpanded = false
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
                            if (selectedActivity != null && reps != null && sets != null) {
                                viewModel.updateWorkout(
                                    selectedActivity!!,
                                    reps!!,
                                    sets!!
                                )
                                navigationRouter.navigateToListScreen()
                            }
                        }) {
                            Text(text = stringResource(id = R.string.save_button_text))
                        }
                    }
                }
                Spacer(Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(id = R.string.reps_label),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    reps?.let {
                        Picker(value = it.toFloat(), onValueChange = { newReps ->
                            reps = newReps.toInt()
                        })
                    }
                }
                Spacer(Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(id = R.string.sets_label),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    sets?.let {
                        Picker(value = it.toFloat(), onValueChange = { newSets ->
                            sets = newSets.toInt()
                        })
                    }
                }
            }
        }
    }
}
