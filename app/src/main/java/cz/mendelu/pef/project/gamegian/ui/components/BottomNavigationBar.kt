package cz.mendelu.pef.project.gamegian.ui.components
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import androidx.compose.ui.graphics.Color


data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector
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
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(item.name) },
                selected = currentRoute == item.route,
                onClick = {
                    navigationRouter.navigateTo(item.route)
                }
            )
        }
    }
}