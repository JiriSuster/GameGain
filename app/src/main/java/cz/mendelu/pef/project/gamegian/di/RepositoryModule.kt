package cz.mendelu.pef.project.gamegian.di

import cz.mendelu.pef.project.gamegian.database.ILocalStudyRepository
import cz.mendelu.pef.project.gamegian.database.ILocalWalkRepository
import cz.mendelu.pef.project.gamegian.database.ILocalWorkoutRepository
import cz.mendelu.pef.project.gamegian.database.LocalStudyRepository
import cz.mendelu.pef.project.gamegian.database.LocalWalkRepository
import cz.mendelu.pef.project.gamegian.database.LocalWorkoutRepository
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
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWalkRepository(dao: WalkDao): ILocalWalkRepository {
        return LocalWalkRepository(dao)
    }

    @Provides
    @Singleton
    fun provideStudyRepository(dao: StudyDao): ILocalStudyRepository {
        return LocalStudyRepository(dao)
    }

    @Provides
    @Singleton
    fun provideWorkoutRepository(dao: WorkoutDao): ILocalWorkoutRepository {
        return LocalWorkoutRepository(dao)
    }

}