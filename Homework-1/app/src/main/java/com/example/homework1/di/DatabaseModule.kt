package com.example.homework1.di

import android.content.Context
import androidx.room.Room
import com.example.homework1.data.dao.ProjectDao
import com.example.homework1.data.dao.TaskDao
import com.example.homework1.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun providesTaskDao(appDatabase: AppDatabase): TaskDao =
        appDatabase.taskDao()

    @Provides
    fun providesProjectDao(appDatabase: AppDatabase): ProjectDao =
        appDatabase.projectDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "TimerApp"
        ).build()
    }
}