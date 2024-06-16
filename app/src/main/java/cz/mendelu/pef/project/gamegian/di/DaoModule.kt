package cz.mendelu.pef.project.gamegian.di

import cz.mendelu.pef.project.gamegian.database.MyDatabase
import cz.mendelu.pef.project.gamegian.database.StudyDao
import cz.mendelu.pef.project.gamegian.database.WalkDao
import cz.mendelu.pef.project.gamegian.database.WorkoutDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideWalkDao(database: MyDatabase): WalkDao {
        return database.walkDao()
    }

    @Provides
    @Singleton
    fun provideStudyDao(database: MyDatabase): StudyDao {
        return database.studyDao()
    }

    @Provides
    @Singleton
    fun provideWorkoutDao(database: MyDatabase): WorkoutDao {
        return database.workoutDao()
    }

}