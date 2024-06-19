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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import cz.mendelu.pef.project.gamegian.ui.components.DonutWithText
import com.chillibits.composenumberpicker.HorizontalNumberPicker
import cz.mendelu.pef.project.gamegian.R
import cz.mendelu.pef.project.gamegian.navigation.bottomNavItems
import cz.mendelu.pef.project.gamegian.ui.components.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneRepMax(navigationRouter: INavigationRouter) {
    val viewModel = hiltViewModel<OneRepMaxViewModel>()
    var weight by remember { mutableFloatStateOf(viewModel.weight) }
    var reps by remember { mutableIntStateOf(viewModel.reps) }
    var oneRepMax by remember { mutableFloatStateOf(viewModel.oneRepMax) }
    var percentages by remember { mutableStateOf(viewModel.percentages) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navigationRouter = navigationRouter, items = bottomNavItems)
        },
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(id = R.string.one_rep_max_title),
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
                text = stringResource(id = R.string.your_one_rep_max),
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
                Text(text = stringResource(id = R.string.weight_label), fontSize = 16.sp)
                Picker(value = weight, onValueChange = {
                    weight = it
                    viewModel.updateWeight(it)
                })
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(id = R.string.reps_label), fontSize = 16.sp)
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
                Text(text = stringResource(id = R.string.calculate_button))
            }
            LazyColumn {
                items(percentages) { (percentage, weight) ->
                    DonutWithText(
                        percentage = percentage,
                        text = "${"%.2f".format(percentage * 100)}%\n${"%.2f".format(weight)} KG"

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
