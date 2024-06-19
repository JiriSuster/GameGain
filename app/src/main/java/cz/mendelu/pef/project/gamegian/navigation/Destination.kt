package cz.mendelu.pef.project.gamegian.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import cz.mendelu.pef.project.gamegian.ui.components.BottomNavItem


val bottomNavItems = listOf(
    BottomNavItem("1RM", Destination.OneRepMax.route, Icons.Default.KeyboardArrowUp),
    BottomNavItem("macro", Destination.MacroCalculator.route, Icons.Default.KeyboardArrowUp),
    BottomNavItem("timer", Destination.HomeScreen.route, Icons.Default.KeyboardArrowUp),
    BottomNavItem("board", Destination.Leaderboard.route, Icons.Default.KeyboardArrowUp),
    BottomNavItem("List", Destination.ListScreen.route, Icons.Default.KeyboardArrowUp)
)
sealed class Destination(val route: String) {
    object HomeScreen : Destination("home_screen")
    object AddTimeScreen : Destination("add_time_screen")
    object Leaderboard : Destination("leaderboard")
    object OneRepMax : Destination("one_rep_max")
    object MacroCalculator : Destination("macro_calculator")
    object FirstScreen : Destination("first_screen")
    object ListScreen : Destination("list_screen")
    object EditWorkout : Destination("edit_workout")
    object EditStudy : Destination("edit_study")
    object EditWalk : Destination("edit_walk")
}