package cz.mendelu.pef.project.gamegian.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import app.futured.donut.compose.DonutProgress
import app.futured.donut.compose.data.DonutModel
import app.futured.donut.compose.data.DonutSection


@Composable
fun DonutWithText(percentage: Float, text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        DonutProgress(
            model = DonutModel(
                cap = 8f,
                masterProgress = 1f,
                gapWidthDegrees = 0f,
                gapAngleDegrees = 90f,
                strokeWidth = 40f,
                backgroundLineColor = Color.LightGray,
                sections = listOf(
                    DonutSection(amount = percentage * 8, color = Color.Cyan)
                )
            ),
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = text,
            style = TextStyle(fontSize = 16.sp, color = Color.Black)
        )
    }
}