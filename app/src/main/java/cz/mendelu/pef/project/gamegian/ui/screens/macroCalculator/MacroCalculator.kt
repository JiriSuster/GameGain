// MacroCalculator.kt
package cz.mendelu.pef.project.gamegian.ui.screens.macroCalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MacroCalculator(){
    val viewModel = hiltViewModel<MacroCalculatorViewModel>()

    val activityOptions = listOf("sedentary", "lightly active", "moderately active", "very active", "extra active")
    val goalOptions = listOf("gain muscle", "maintain weight", "lose body fat")

    val (selectedActivity, setSelectedActivity) = remember { mutableStateOf(activityOptions[0]) }
    val (activityExpanded, setActivityExpanded) = remember { mutableStateOf(false) }

    val (selectedGoal, setSelectedGoal) = remember { mutableStateOf(goalOptions[0]) }
    val (goalExpanded, setGoalExpanded) = remember { mutableStateOf(false) }

    val (age, setAge) = remember { mutableStateOf("") }
    val (weight, setWeight) = remember { mutableStateOf("") }
    val (height, setHeight) = remember { mutableStateOf("") }

    val (result, setResult) = remember { mutableStateOf<MacroResult?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "macro calculator",
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 14.sp
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "activity:", modifier = Modifier.fillMaxWidth(0.8f))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                ExposedDropdownMenuBox(expanded = activityExpanded, onExpandedChange = setActivityExpanded) {
                    TextField(
                        modifier = Modifier.menuAnchor(),
                        value = selectedActivity,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = activityExpanded) }
                    )
                    ExposedDropdownMenu(
                        expanded = activityExpanded,
                        onDismissRequest = { setActivityExpanded(!activityExpanded) }) {
                        activityOptions.forEachIndexed { index, activity ->
                            DropdownMenuItem(
                                text = { Text(text = activity) },
                                onClick = {
                                    setSelectedActivity(activityOptions[index])
                                    setActivityExpanded(false)
                                }, contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                        }
                    }
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = null)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "goal:", modifier = Modifier.fillMaxWidth(0.8f))
            ExposedDropdownMenuBox(expanded = goalExpanded, onExpandedChange = setGoalExpanded) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    value = selectedGoal,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = goalExpanded) }
                )
                ExposedDropdownMenu(
                    expanded = goalExpanded,
                    onDismissRequest = { setGoalExpanded(!goalExpanded) }) {
                    goalOptions.forEachIndexed { index, goal ->
                        DropdownMenuItem(
                            text = { Text(text = goal) },
                            onClick = {
                                setSelectedGoal(goalOptions[index])
                                setGoalExpanded(false)
                            }, contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = age,
                onValueChange = setAge,
                label = { Text("Age") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xff6750a4)
                )
            )

            OutlinedTextField(
                value = weight,
                onValueChange = setWeight,
                label = { Text("Weight") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xff6750a4)
                )
            )

            OutlinedTextField(
                value = height,
                onValueChange = setHeight,
                label = { Text("Height") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xff6750a4)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val ageInt = age.toIntOrNull()
                    val weightDouble = weight.toDoubleOrNull()
                    val heightDouble = height.toDoubleOrNull()

                    if (ageInt != null && weightDouble != null && heightDouble != null) {
                        setResult(viewModel.calculateMacros(ageInt, weightDouble, heightDouble, selectedActivity, selectedGoal))
                    }
                },
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "calculate")
            }

            Spacer(modifier = Modifier.height(16.dp))

            result?.let {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Your daily intake:", fontWeight = FontWeight.Bold)
                    Row {
                        NutrientItem("Protein:", it.protein)
                        Spacer(modifier = Modifier.width(4.dp))
                        NutrientItem("Carbs:", it.carbs)
                        Spacer(modifier = Modifier.width(4.dp))
                        NutrientItem("Fat:", it.fat)
                    }
                }
            }
        }
    }
}
@Composable
fun NutrientItem(label: String, value: Int) {
    Box(
        modifier = Modifier
            .background(color = Color(0xFFFEF7FF))
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Text(text = "$label \n$value g")
    }
}