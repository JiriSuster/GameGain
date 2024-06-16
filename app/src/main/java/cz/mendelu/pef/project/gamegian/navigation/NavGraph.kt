package cz.mendelu.pef.project.gamegian.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.mendelu.pef.project.gamegian.ui.screens.addScreen.AddScreen
import cz.mendelu.pef.project.gamegian.ui.screens.HomeScreen
import cz.mendelu.pef.project.gamegian.ui.screens.ListScreen
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
){

    NavHost(navController = navHostController, startDestination = startDestination){

        composable(Destination.OneRepMax.route){
            OneRepMax()
        }

        composable(Destination.HomeScreen.route){
            HomeScreen()
        }

        composable(Destination.MacroCalculator.route){
            MacroCalculator()
        }

        composable(Destination.FirstScreen.route){
            FirstScreen()
        }

        composable(Destination.AddTimeScreen.route){
            AddScreen(navigationRouter)
        }

        composable(Destination.ListScreen.route){
            ListScreen(navigationRouter = navigationRouter)
        }




    }


}