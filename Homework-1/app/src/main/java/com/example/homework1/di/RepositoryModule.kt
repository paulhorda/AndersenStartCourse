package com.example.homework1.di

import com.example.homework1.data.dao.ProjectDao
import com.example.homework1.data.dao.TaskDao
import com.example.homework1.data.repository.ProjectRepository
import com.example.homework1.data.repository.TaskRepository
import com.example.homework1.data.repository.impl.ProjectRepositoryImpl
import com.example.homework1.data.repository.impl.TaskRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideProjectRepository(projectDao: ProjectDao): ProjectRepository {
        return ProjectRepositoryImpl(projectDao)
    }

    @Provides
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepositoryImpl(taskDao)
    }
}