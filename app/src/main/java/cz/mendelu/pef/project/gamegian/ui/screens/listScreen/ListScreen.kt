package cz.mendelu.pef.project.gamegian.ui.screens.listScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.mendelu.pef.project.gamegian.model.Study
import cz.mendelu.pef.project.gamegian.model.Walk
import cz.mendelu.pef.project.gamegian.model.Workout
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import cz.mendelu.pef.project.gamegian.ui.components.StudyCard
import cz.mendelu.pef.project.gamegian.ui.components.WalkCard
import cz.mendelu.pef.project.gamegian.ui.components.WorkoutCard
import androidx.hilt.navigation.compose.hiltViewModel
import cz.mendelu.pef.project.gamegian.R
import cz.mendelu.pef.project.gamegian.navigation.bottomNavItems
import cz.mendelu.pef.project.gamegian.ui.components.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navigationRouter: INavigationRouter) {
    val viewModel = hiltViewModel<ListViewModel>()
    val combined by viewModel.combined.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadAll()
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navigationRouter = navigationRouter, items = bottomNavItems)
        },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navigationRouter.navigateToHomeScreen()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(id = R.string.activities_title),
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 14.sp
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigationRouter.navigateToAddScreen()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }

    ) {
        Column(Modifier.padding(it)) {
            if (combined.isNotEmpty()) {
                LazyColumn {
                    items(combined) { item ->
                        when (item) {
                            is Study -> StudyCard(item, viewModel::deleteItem, navigationRouter)
                            is Walk -> WalkCard(item, viewModel::deleteItem, navigationRouter)
                            is Workout -> WorkoutCard(item, viewModel::deleteItem, navigationRouter)
                            else -> Text(stringResource(id = R.string.no_activities_message))
                        }
                    }
                }
            } else {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(id = R.string.no_activities_message), style = TextStyle(fontSize = 25.sp), modifier = Modifier.align(Alignment.CenterHorizontally))
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(id = R.string.add_new_activity),
                            style = TextStyle(fontSize = 25.sp)
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 80.dp)
                                .size(100.dp)
                        )
                    }
                }
            }
        }
    }
}
