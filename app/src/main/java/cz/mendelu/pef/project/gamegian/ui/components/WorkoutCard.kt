package cz.mendelu.pef.project.gamegian.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.mendelu.pef.project.gamegian.toReadableTime

@Composable
fun WorkoutCard(reps: Int, sets: Int, name: String, time: Long) {
    Row {
        // Checkbox
        Checkbox(checked = false, onCheckedChange = { /* TODO: Handle checkbox state */ })

        // Content column
        Column {
            Text(text = name + " ${reps}x${sets}")
            Text(text = time.toReadableTime())
        }

        // Pencil icon button
        IconButton(onClick = { /* TODO: Handle edit action */ }) {
            Icon(imageVector = Icons.Default.Create, contentDescription = null)
        }

        // Trash can icon button
        IconButton(onClick = { /* TODO: Handle edit action */ }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        }
    }
}

@Preview
@Composable
fun WorkoutcardPreview() {
    // Example usage of StudyCard in a preview
    WorkoutCard(1, 30,"biceps curls", 5400000L) // Example values: 1 hour 30 minutes = 5400000 milliseconds
}
