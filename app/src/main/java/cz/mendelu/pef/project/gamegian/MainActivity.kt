package cz.mendelu.pef.project.gamegian

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cz.mendelu.pef.project.gamegian.navigation.Destination
import cz.mendelu.pef.project.gamegian.navigation.NavGraph
import cz.mendelu.pef.project.gamegian.ui.theme.GameGainTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        setContent {
            GameGainTheme {
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

        checkSystemLanguage()
    }

    private fun isFirstLaunch(): Boolean {
        val isFirstLaunch = sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true)
        return isFirstLaunch
    }

    fun setFirstLaunchComplete() {
        sharedPreferences.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
    }

    private fun checkSystemLanguage() {
        val locale = getCurrentLocale()
        if (locale.language == "cs") {
            resources.configuration.setLocale(locale)
            resources.updateConfiguration(resources.configuration, resources.displayMetrics)
        } else {
            resources.configuration.setLocale(Locale.getDefault())
            resources.updateConfiguration(resources.configuration, resources.displayMetrics)
        }
    }

    private fun getCurrentLocale(): Locale {
        val configuration = resources.configuration
        return configuration.locales[0]
    }

    companion object {
        private const val KEY_FIRST_LAUNCH = "first_launch"
    }
}
