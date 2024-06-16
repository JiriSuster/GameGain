package cz.mendelu.pef.project.gamegian.navigation

sealed class Destination(val route: String) {
    object HomeScreen : Destination("home_screen")
    object AddTimeScreen : Destination("add_time_screen")
    //object Leaderboard : Destination("leaderboard")
    object OneRepMax : Destination("one_rep_max")
    object MacroCalculator : Destination("macro_calculator")
    object FirstScreen : Destination("first_screen")
    object ListScreen : Destination("list_screen")
}