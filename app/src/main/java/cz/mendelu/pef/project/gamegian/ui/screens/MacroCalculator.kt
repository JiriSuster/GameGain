package cz.mendelu.pef.project.gamegian.ui.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MacroCalculator() {
    val activityOptions = listOf("sedentary", "lightly active", "moderately active", "very active", "super active")
    val goalOptions = listOf("gain muscle", "lose weight", "maintain weight")

    val (selectedActivity, setSelectedActivity) = remember { mutableStateOf(activityOptions[0]) }
    val (activityExpanded, setActivityExpanded) = remember { mutableStateOf(false) }

    val (selectedGoal, setSelectedGoal) = remember { mutableStateOf(goalOptions[0]) }
    val (goalExpanded, setGoalExpanded) = remember { mutableStateOf(false) }

    val (age, setAge) = remember { mutableStateOf("") }
    val (weight, setWeight) = remember { mutableStateOf("") }
    val (height, setHeight) = remember { mutableStateOf("") }

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
                    TextField(modifier = Modifier.menuAnchor(),
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
            
            ExposedDropdownMenuBox(expanded = goalExpanded, onExpandedChange = setGoalExpanded) {
                TextField(modifier = Modifier.menuAnchor(),
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

            TextField(
                value = age,
                onValueChange = setAge,
                label = { Text("Age") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 8.dp)
            )

            TextField(
                value = weight,
                onValueChange = setWeight,
                label = { Text("Weight") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 8.dp)
            )

            TextField(
                value = height,
                onValueChange = setHeight,
                label = { Text("Height") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "calculate")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Placeholder for the result section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Your daily intake:", fontWeight = FontWeight.Bold)
                listOf(
                    "Protein: 75 - 246g",
                    "Carbs: 308g - 534g",
                    "Fat: 66g - 115g"
                ).forEach { intake ->
                    Text(text = intake)
                }
            }
        }
    }
}
