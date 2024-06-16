package cz.mendelu.pef.project.gamegian.ui.screens.oneRepMax

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chillibits.composenumberpicker.HorizontalNumberPicker
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import cz.mendelu.pef.project.gamegian.ui.components.DonutWithText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneRepMax(navigationRouter: INavigationRouter) {
    val viewModel = hiltViewModel<OneRepMaxViewModel>()
    var weight by remember { mutableFloatStateOf(viewModel.weight) }
    var reps by remember { mutableIntStateOf(viewModel.reps) }
    var oneRepMax by remember { mutableFloatStateOf(viewModel.oneRepMax) }
    var percentages by remember { mutableStateOf(viewModel.percentages) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "One Rep Max",
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 14.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigationRouter.navigateToHomeScreen()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(paddingValues = it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your one rep max is:",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "%.2f KG".format(oneRepMax),
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "weight", fontSize = 16.sp)
                    Picker(value = weight, onValueChange = {
                        weight = it
                        viewModel.updateWeight(it)
                    })
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "reps", fontSize = 16.sp)
                    Picker(value = reps.toFloat(), onValueChange = {
                        reps = it.toInt()
                        viewModel.updateReps(it.toInt())
                    })
                }

            Button(
                onClick = {
                    viewModel.calculateOneRepMax()
                    oneRepMax = viewModel.oneRepMax
                    percentages = viewModel.percentages
                },
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(text = "calculate")
            }
            LazyColumn {
                items(percentages) { (percentage, weight) ->
                    DonutWithText(
                        percentage = percentage,
                        text = "${percentage * 100}%\n" + "%.2f KG".format(weight)
                    )
                }
            }
        }
    }
}

@Composable
fun Picker(value: Float, onValueChange: (Float) -> Unit) {
    HorizontalNumberPicker(
        min = 0,
        max = 300,
        default = value.toInt(),
        modifier = Modifier.padding(10.dp),
        onValueChange = { onValueChange(it.toFloat()) }
    )
}
