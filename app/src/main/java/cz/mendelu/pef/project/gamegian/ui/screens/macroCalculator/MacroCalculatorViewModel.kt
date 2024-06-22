package cz.mendelu.pef.project.gamegian.ui.screens.macroCalculator
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.project.gamegian.MyDataStore
import cz.mendelu.pef.project.gamegian.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class MacroCalculatorViewModel @Inject constructor(
    private val myDataStore: MyDataStore
) : ViewModel() {

    private var isMale: Boolean = true

    init {
        viewModelScope.launch {
            myDataStore.watchGender().collect { gender ->
                isMale = gender
            }
        }
    }

    fun calculateMacros(
        context: Context,
        age: Int,
        weight: Double,
        height: Double,
        activityLevel: String,
        goal: String
    ): MacroResult {
        val bmr = if (isMale) {
            10 * weight + 6.25 * height - 5 * age + 5 // Mifflin-St Jeor Equation for men
        } else {
            10 * weight + 6.25 * height - 5 * age - 161 // Mifflin-St Jeor Equation for women
        }

        val activityMultiplier = when (activityLevel) {
            context.getString(R.string.sedentary) -> 1.2
            context.getString(R.string.lightly_active) -> 1.375
            context.getString(R.string.moderately_active) -> 1.55
            context.getString(R.string.very_active) -> 1.725
            context.getString(R.string.extra_active) -> 1.9
            else -> 1.2
        }

        val maintenanceCalories = bmr * activityMultiplier

        val calorieAdjustment = when (goal) {
            context.getString(R.string.gain_muscle) -> 500
            context.getString(R.string.maintain_weight) -> 0
            context.getString(R.string.lose_body_fat) -> -500
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
