package cz.mendelu.pef.project.gamegian.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import cz.mendelu.pef.project.gamegian.R
import cz.mendelu.pef.project.gamegian.navigation.Destination

data class BottomNavItem(
    val name: String,
    val route: String,
    val iconResId: Int
)

val bottomNavItems = listOf(
    BottomNavItem("1RM", Destination.OneRepMax.route, R.drawable.onerm),
    BottomNavItem("Macro", Destination.MacroCalculator.route, R.drawable.calc),
    BottomNavItem("Timer", Destination.HomeScreen.route, R.drawable.timer),
    BottomNavItem("List", Destination.ListScreen.route, R.drawable.list),
    BottomNavItem("Board", Destination.Leaderboard.route, R.drawable.leaderboard)
)

@Composable
fun BottomNavigationBar(
    navigationRouter: INavigationRouter,
    items: List<BottomNavItem>
) {
    BottomNavigation(
        backgroundColor = Color(0xFFF4EDFF)
    ) {
        val navController = navigationRouter.getNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.iconResId), contentDescription = item.name, modifier = Modifier.size(24.dp)) },
                label = { Text(item.name) },
                selected = currentRoute == item.route,
                onClick = {
                    navigationRouter.navigateTo(item.route)
                }
            )
        }
    }
}
