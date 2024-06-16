package cz.mendelu.pef.project.gamegian.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import cz.mendelu.pef.project.gamegian.navigation.Destination
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import cz.mendelu.pef.project.gamegian.model.Study
import cz.mendelu.pef.project.gamegian.model.Walk
import cz.mendelu.pef.project.gamegian.model.Workout
import cz.mendelu.pef.project.gamegian.ui.components.StudyCard
import cz.mendelu.pef.project.gamegian.ui.components.WalkCard
import cz.mendelu.pef.project.gamegian.ui.components.WorkoutCard


@Composable
fun ListScreen(navigationRouter: INavigationRouter) {
    val viewModel = hiltViewModel<ListViewModel>()
    val combined by viewModel.combined.collectAsState()

    Column {
        LazyColumn {
            items(combined) { item ->
                when (item) {
                    is Study -> StudyCard(hours = item.studyHours.toString(), minutes = item.studyMinutes.toString(), time = item.time)
                    is Walk -> WalkCard(steps = item.steps, time = item.time)
                    is Workout -> WorkoutCard(reps = item.reps, sets = item.sets, name = item.exercise_name, time = item.time)
                    else -> Text("Unsupported item type")
                }
            }
        }
    }

    viewModel.loadAll()
}

