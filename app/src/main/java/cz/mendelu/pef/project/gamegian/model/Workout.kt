package cz.mendelu.pef.project.gamegian.model

import androidx.room.Entity
import androidx.room.PrimaryKey

    @Entity(tableName = "workout")
    data class Workout(var time: Long){

        @PrimaryKey(autoGenerate = true)
        var id: Long? = null

        var reps: Int = 0
        var sets: Int = 0
        var exercise_name: String = "name"
        var date: Long = 0


    }

