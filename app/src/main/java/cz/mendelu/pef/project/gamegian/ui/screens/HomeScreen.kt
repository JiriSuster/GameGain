package cz.mendelu.pef.project.gamegian.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.mendelu.pef.project.gamegian.MyDataStore
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import cz.mendelu.pef.project.gamegian.toReadableTime
import kotlinx.coroutines.flow.first
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import cz.mendelu.pef.project.gamegian.ui.screens.addScreen.AddScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigationRouter: INavigationRouter,
    viewModel: AddScreenViewModel = hiltViewModel()
) {
    val myDataStore = viewModel.myDataStore

    // Fetch the app version using PackageManager
    val context = LocalContext.current
    val packageInfo = remember {
        context.packageManager.getPackageInfo(context.packageName, 0)
    }
    val appVersion = packageInfo.versionName

    // Observe the username
    val usernameState by produceState<String?>(initialValue = null) {
        value = myDataStore.watchUsername().first()
    }
    val username = usernameState

    // Observe the time
    val timeState by produceState<Long?>(initialValue = null) {
        value = myDataStore.watchTime().first()
    }
    val time = timeState ?: 0L

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "timer",
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
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {navigationRouter.navigateToMacroCalculator()}) {
                Text(text = "macro calculator")

            }
            Button(onClick = {navigationRouter.navigateToOneRepMax()}) {
                Text(text = "one rep max")

            }
            Text(text = appVersion)
            Text(
                text = "Welcome, ${username ?: "Guest"}!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "You can play for:",
                fontSize = 16.sp,
            )

            Text(
                text = time.toReadableTime(),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* start timer logic */ },
                modifier = Modifier
                    .height(65.dp)
                    .width(200.dp)
                    .shadow(4.dp, RoundedCornerShape(30.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF4EDFF),
                    contentColor = Color(0xFF7E57C2)
                )
            ) {
                Text(text = "start timer")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { navigationRouter.navigateToAddScreen() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A4)),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Icon",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "add time", color = Color.White)
                }
                Button(
                    onClick = { navigationRouter.navigateToListScreen() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A4)),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "activities", color = Color.White)
                }
            }
        }
    }
}
