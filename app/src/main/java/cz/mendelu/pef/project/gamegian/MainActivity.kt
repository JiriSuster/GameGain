package cz.mendelu.pef.project.gamegian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cz.mendelu.pef.project.gamegian.ui.screens.macroCalculator.MacroCalculator
import cz.mendelu.pef.project.gamegian.ui.theme.GameGainTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameGainTheme {
                MacroCalculator()
            }
        }
    }
}
