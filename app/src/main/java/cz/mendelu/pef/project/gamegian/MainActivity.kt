package cz.mendelu.pef.project.gamegian

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cz.mendelu.pef.project.gamegian.navigation.Destination
import cz.mendelu.pef.project.gamegian.navigation.NavGraph
import cz.mendelu.pef.project.gamegian.ui.theme.GameGainTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        setContent {
            GameGainTheme {
                // Determine which screen to show based on first launch status
                val startDestination = remember {
                    if (isFirstLaunch()) {
                        Destination.FirstScreen.route
                    } else {
                        Destination.HomeScreen.route
                    }
                }
                NavGraph(startDestination = startDestination)
            }
        }
    }

    private fun isFirstLaunch(): Boolean {
        val isFirstLaunch = sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true)
        if (isFirstLaunch) {
            // Mark that app has been launched at least once
            sharedPreferences.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
        }
        return isFirstLaunch
    }

    companion object {
        private const val KEY_FIRST_LAUNCH = "first_launch"
    }
}
