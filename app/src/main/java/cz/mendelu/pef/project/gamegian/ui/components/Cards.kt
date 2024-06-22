package cz.mendelu.pef.project.gamegian.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.project.gamegian.R
import cz.mendelu.pef.project.gamegian.model.Study
import cz.mendelu.pef.project.gamegian.model.Walk
import cz.mendelu.pef.project.gamegian.model.Workout
import cz.mendelu.pef.project.gamegian.navigation.INavigationRouter
import cz.mendelu.pef.project.gamegian.toReadableTime

@Composable
fun StudyCard(study: Study, onDelete: (Any) -> Unit, navigationRouter: INavigationRouter) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.background(Color(0xFFF4EDFF)).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "${stringResource(R.string.study_card_studying)} ${study.studyHours}h ${study.studyMinutes}m")
                Text(text = "+ " + study.time.toReadableTime())
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { navigationRouter.navigateToEditStudy(study.id!!) }) {
                Icon(imageVector = Icons.Default.Create, contentDescription = null)
            }

            IconButton(onClick = { onDelete(study) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}

@Composable
fun WalkCard(walk: Walk, onDelete: (Any) -> Unit, navigationRouter: INavigationRouter) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.background(Color(0xFFF4EDFF)).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "${walk.steps} ${stringResource(R.string.walk_card_steps)}")
                Text(text = "+ " + walk.time.toReadableTime())
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { navigationRouter.navigateToEditWalk(walk.id!!) }) {
                Icon(imageVector = Icons.Default.Create, contentDescription = null)
            }

            IconButton(onClick = { onDelete(walk) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}

@Composable
fun WorkoutCard(workout: Workout, onDelete: (Any) -> Unit, navigationRouter: INavigationRouter) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.background(Color(0xFFF4EDFF)).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "${workout.exercise_name} ${workout.reps}x${workout.sets}")
                Text(text = "+ " + workout.time.toReadableTime())
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { navigationRouter.navigateToEditWorkout(workout.id!!) }) {
                Icon(imageVector = Icons.Default.Create, contentDescription = null)
            }

            IconButton(onClick = { onDelete(workout) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}
