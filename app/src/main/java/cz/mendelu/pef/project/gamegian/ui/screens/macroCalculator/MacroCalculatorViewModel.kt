package cz.mendelu.pef.project.gamegian.ui.screens.macroCalculator

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class MacroCalculatorViewModel @Inject constructor() : ViewModel() {

    fun calculateMacros(age: Int, weight: Double, height: Double, activityLevel: String, goal: String): MacroResult {
        val bmr = 10 * weight + 6.25 * height - 5 * age + 5 // Mifflin-St Jeor Equation for men
        val bmr2 = 10 * weight + 6.25 * height - 5 * age + -161 // Mifflin-St Jeor Equation for women

        val activityMultiplier = when (activityLevel) {
            "sedentary" -> 1.2
            "lightly active" -> 1.375
            "moderately active" -> 1.55
            "very active" -> 1.725
            "extra active" -> 1.9
            else -> 1.2
        }

        val maintenanceCalories = bmr * activityMultiplier

        val calorieAdjustment = when (goal) {
            "gain muscle" -> 500
            "maintain weight" -> 0
            "lose body fat" -> -500
            else -> 0
        }

        val targetCalories = maintenanceCalories + calorieAdjustment

        val protein = (targetCalories * 0.15 / 4).roundToInt()
        val carbs = (targetCalories * 0.55 / 4).roundToInt()
        val fat = (targetCalories * 0.3 / 9).roundToInt()

        return MacroResult(protein, carbs, fat)
    }
}

data class MacroResult(val protein: Int, val carbs: Int, val fat: Int)
