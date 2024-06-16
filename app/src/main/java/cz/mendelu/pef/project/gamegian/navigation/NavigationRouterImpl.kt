package cz.mendelu.pef.project.gamegian.navigation

import androidx.navigation.NavController
import cz.mendelu.pef.project.gamegian.model.Workout

class NavigationRouterImpl(private val navController: NavController) : INavigationRouter {
    override fun navigateToHomeScreen() {
        navController.navigate(Destination.HomeScreen.route)
    }

    override fun getNavController(): NavController {
        return navController
    }

    override fun navigateToFirstScreen() {
        navController.navigate(Destination.FirstScreen.route)
    }

    override fun navigateToOneRepMax() {
        navController.navigate(Destination.OneRepMax.route)
    }

    override fun navigateToMacroCalculator() {
        navController.navigate(Destination.MacroCalculator.route)
    }

    override fun navigateToAddScreen() {
        navController.navigate(Destination.AddTimeScreen.route)
    }

    override fun navigateToListScreen() {
        navController.navigate(Destination.ListScreen.route)
    }

    override fun navigateToEditWorkout(id: Long) {
    navController.navigate(Destination.EditWorkout.route + "/" + id)
    }

    override fun returnBack() {
        navController.popBackStack()
    }


}