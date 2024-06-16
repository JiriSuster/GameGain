package cz.mendelu.pef.project.gamegian.model

import androidx.room.Entity
import androidx.room.PrimaryKey

    @Entity(tableName = "study")
    data class Study(var time: Long){

        @PrimaryKey(autoGenerate = true)
        var id: Long? = null

        var studyHours: Int = 0
        var studyMinutes: Int = 0


    }
