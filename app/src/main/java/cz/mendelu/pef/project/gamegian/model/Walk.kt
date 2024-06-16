package cz.mendelu.pef.project.gamegian.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "walk")
data class Walk(var time: Long){

        @PrimaryKey(autoGenerate = true)
        var id: Long? = null

        var steps: Int = 0

}

