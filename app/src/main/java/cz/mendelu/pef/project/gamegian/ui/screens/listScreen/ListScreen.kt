package cz.mendelu.pef.project.gamegian.ui.screens.listScreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    LaunchedEffect(Unit) {
        viewModel.loadAll()
    }

    Column {
        LazyColumn {
            items(combined) { item ->
                when (item) {
                    is Study -> StudyCard(item, viewModel::deleteItem)
                    is Walk -> WalkCard(item, viewModel::deleteItem)
                    is Workout -> WorkoutCard(item, viewModel::deleteItem, navigationRouter)
                    else -> Text("Unsupported item type")
                }
            }
        }
    }
}
