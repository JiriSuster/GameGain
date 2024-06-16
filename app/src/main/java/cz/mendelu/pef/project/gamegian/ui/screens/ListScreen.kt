package cz.mendelu.pef.project.gamegian.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import cz.mendelu.pef.project.gamegian.navigation.Destination
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter

@Composable
fun ListScreen(navigationRouter: INavigationRouter) {
    val viewModel = hiltViewModel<ListViewModel>()

}