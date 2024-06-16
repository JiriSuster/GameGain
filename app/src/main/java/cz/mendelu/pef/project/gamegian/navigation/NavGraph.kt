package cz.mendelu.pef.project.gamegian.navigation

import LeaderBoardScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.mendelu.pef.project.gamegian.ui.screens.EditWalkScreen.EditWalkScreen
import cz.mendelu.pef.project.gamegian.ui.screens.editWorkout.EditWorkout
import cz.mendelu.pef.project.gamegian.ui.screens.addScreen.AddScreen
import cz.mendelu.pef.project.gamegian.ui.screens.HomeScreen
import cz.mendelu.pef.project.gamegian.ui.screens.EditStudyScreen.EditStudyScreen
import cz.mendelu.pef.project.gamegian.ui.screens.listScreen.ListScreen
import cz.mendelu.pef.project.gamegian.ui.screens.firstScreen.FirstScreen
import cz.mendelu.pef.project.gamegian.ui.screens.macroCalculator.MacroCalculator
import cz.mendelu.pef.project.gamegian.ui.screens.oneRepMax.OneRepMax


@Composable
fun NavGraph(
    navHostController: NavHostController = rememberNavController(),
    navigationRouter: INavigationRouter = remember {
        NavigationRouterImpl(navController = navHostController)
    },
    startDestination: String
) {

    NavHost(navController = navHostController, startDestination = startDestination) {

        composable(Destination.OneRepMax.route) {
            OneRepMax(navigationRouter)
        }

        composable(Destination.HomeScreen.route) {
            HomeScreen(navigationRouter)
        }

        composable(Destination.MacroCalculator.route) {
            MacroCalculator(navigationRouter)
        }

        composable(Destination.FirstScreen.route) {
            FirstScreen(navigationRouter)
        }

        composable(Destination.AddTimeScreen.route) {
            AddScreen(navigationRouter)
        }

        composable(Destination.ListScreen.route) {
            ListScreen(navigationRouter = navigationRouter)
        }

        composable(Destination.EditWorkout.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )) 
        {
            val id = it.arguments?.getLong("id")
            EditWorkout(navigationRouter = navigationRouter, id = id!!)
        }

        composable(Destination.EditStudy.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            ))
        {
            val id = it.arguments?.getLong("id")
            EditStudyScreen(navigationRouter = navigationRouter, id = id!!)
        }


        composable(Destination.EditWalk.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            ))
        {
            val id = it.arguments?.getLong("id")
            EditWalkScreen(navigationRouter = navigationRouter, id = id!!)
        }

        composable(Destination.Leaderboard.route) {
            LeaderBoardScreen(navigationRouter = navigationRouter)
        }



    }

}