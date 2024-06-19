package cz.mendelu.pef.project.gamegian


fun Long.toReadableTime(): String {

    val totalSeconds = this
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    return when {
        hours > 0 -> String.format("%d h %d m %d s", hours, minutes, seconds)
        minutes > 0 -> String.format("%d m %d s", minutes, seconds)
        else -> String.format("%d s", seconds)
    }
}
