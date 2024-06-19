package cz.mendelu.pef.project.gamegian.navigation

import androidx.navigation.NavController
import cz.mendelu.pef.project.gamegian.model.Workout

interface INavigationRouter {
    fun navigateToHomeScreen()
    fun getNavController(): NavController

    fun navigateToFirstScreen()
    fun navigateToOneRepMax()
    fun navigateToMacroCalculator()
    fun navigateToAddScreen()
    fun navigateToLeaderBoard()
    fun navigateToListScreen()
    fun navigateToEditWorkout(id: Long)
    fun navigateToEditStudy(id: Long)
    fun navigateToEditWalk(id: Long)
    fun navigateTo(route: String)
    fun returnBack()
}