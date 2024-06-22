package cz.mendelu.pef.project.gamegian.ui.screens.editWorkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import cz.mendelu.pef.project.gamegian.ui.screens.addScreen.Picker
import cz.mendelu.pef.project.gamegian.R
import cz.mendelu.pef.project.gamegian.ui.components.BottomNavigationBar
import cz.mendelu.pef.project.gamegian.ui.components.bottomNavItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditWorkout(navigationRouter: INavigationRouter, id: Long) {
    val viewModel = hiltViewModel<EditWorkoutsViewModel>()
    val activityOptions = listOf(
        stringResource(R.string.activity_biceps_curls),
        stringResource(R.string.activity_push_ups),
        stringResource(R.string.activity_sit_ups),
        stringResource(R.string.activity_pull_ups),
        stringResource(R.string.activity_dips)
    )
    var activityExpanded by remember { mutableStateOf(false) }
    var selectedActivity by remember { mutableStateOf<String?>(null) }
    var reps by remember { mutableStateOf<Int?>(null) }
    var sets by remember { mutableStateOf<Int?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }
    var (showError, setShowError) = remember { mutableStateOf(false) }
    val errorMessage = stringResource(id = R.string.error_invalid_input)

    LaunchedEffect(key1 = id) {
        viewModel.loadWorkout(id)
    }

    LaunchedEffect(viewModel.currentWorkout) {
        viewModel.currentWorkout?.let {
            selectedActivity = it.exercise_name
            reps = it.reps
            sets = it.sets
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
                        text = stringResource(id = R.string.edit_workout_title),
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
                            if (selectedActivity != null && reps != null && sets != null && reps != 0 && sets != 0) {
                                viewModel.updateWorkout(
                                    selectedActivity!!,
                                    reps!!,
                                    sets!!
                                )
                                navigationRouter.navigateToListScreen()
                            }else{
                                setShowError(true)
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
}
