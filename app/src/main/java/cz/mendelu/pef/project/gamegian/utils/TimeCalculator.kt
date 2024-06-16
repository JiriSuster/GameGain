package cz.mendelu.pef.project.gamegian.utils

fun calculateTime(
    walkingSteps: Int = 0,
    workoutSets: Int = 0,
    workoutReps: Int = 0,
    studyHours: Int = 0,
    studyMinutes: Int = 0
): Long {
    val walkingRewardSeconds = walkingSteps * 0.18
    val workoutRewardSeconds = workoutSets * 400 + workoutReps * 65
    val studyRewardSeconds = (studyHours * 3600 + studyMinutes * 60) * 15 / 60

    return (walkingRewardSeconds + workoutRewardSeconds + studyRewardSeconds).toLong()
}