package cz.mendelu.pef.project.gamegian


fun Long.toReadableTime(): String {
    val seconds = this.toInt()
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60

    return if (hours > 0) {
        String.format("%d h %d m", hours, minutes)
    } else {
        String.format("%d m", minutes)
    }
}