package cz.mendelu.pef.project.gamegian.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cz.mendelu.pef.project.gamegian.model.Study
import cz.mendelu.pef.project.gamegian.model.Walk
import cz.mendelu.pef.project.gamegian.model.Workout

@Database(entities = [Walk::class,Study::class,Workout::class], version = 2, exportSchema = true)
abstract class MyDatabase : RoomDatabase() {

    abstract fun studyDao(): StudyDao
    abstract fun walkDao(): WalkDao
    abstract fun workoutDao(): WorkoutDao

    companion object {
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            if (INSTANCE == null) {
                synchronized(MyDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MyDatabase::class.java, "my_database"
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

}