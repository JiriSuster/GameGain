package cz.mendelu.pef.project.gamegian.navigation

import androidx.navigation.NavController

interface INavigationRouter {
    fun navigateToHomeScreen()
    fun getNavController(): NavController

    fun navigateToFirstScreen()
    fun navigateToOneRepMax()
    fun navigateToMacroCalculator()
    fun navigateToAddScreen()
    //fun navigateToLeaderBoard()
    fun navigateToListScreen()

    fun returnBack()
}