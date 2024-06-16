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
import cz.mendelu.pef.project.gamegian.model.Study
import cz.mendelu.pef.project.gamegian.model.Walk
import cz.mendelu.pef.project.gamegian.model.Workout
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import cz.mendelu.pef.project.gamegian.toReadableTime

@Composable
fun StudyCard(study: Study, onDelete: (Any) -> Unit,navigationRouter: INavigationRouter) {
    Row {
       // Checkbox(checked = false, onCheckedChange = {  })

        Column {
            Text(text = "studying ${study.studyHours}h ${study.studyMinutes}m")
            Text(text = study.time.toReadableTime())
        }

        IconButton(onClick = { navigationRouter.navigateToEditStudy(study.id!!) }) {
            Icon(imageVector = Icons.Default.Create, contentDescription = null)
        }

        IconButton(onClick = { onDelete(study) }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        }
    }
}


@Composable
fun WalkCard(walk: Walk, onDelete: (Any) -> Unit, navigationRouter: INavigationRouter) {
    Row {
        //Checkbox(checked = false, onCheckedChange = { /* TODO: Handle checkbox state */ })

        Column {
            Text(text = "${walk.steps} steps")
            Text(text = walk.time.toReadableTime())
        }

        IconButton(onClick = { navigationRouter.navigateToEditWalk(walk.id!!) }) {
            Icon(imageVector = Icons.Default.Create, contentDescription = null)
        }

        IconButton(onClick = { onDelete(walk) }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        }
    }
}

@Composable
fun WorkoutCard(workout: Workout, onDelete: (Any) -> Unit, navigationRouter: INavigationRouter) {
    Row {
        //Checkbox(checked = false, onCheckedChange = { /* TODO: Handle checkbox state */ })

        Column {
            Text(text = "${workout.exercise_name} ${workout.reps}x${workout.sets}")
            Text(text = workout.time.toReadableTime())
        }

        IconButton(onClick = { navigationRouter.navigateToEditWorkout(workout.id!!) }) {
            Icon(imageVector = Icons.Default.Create, contentDescription = null)
        }

        IconButton(onClick = { onDelete(workout) }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        }
    }
}
