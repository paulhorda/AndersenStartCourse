package com.example.homework1.di

import com.example.homework1.data.repository.ProjectRepository
import com.example.homework1.data.repository.TaskRepository
import com.example.homework1.ui.controller.ProjectController
import com.example.homework1.ui.controller.TaskController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ControllerModule {
    @Provides
    fun provideProjectController(projectRepository: ProjectRepository): ProjectController {
        return ProjectController(projectRepository)
    }

    @Provides
    fun provideTaskController(taskRepository: TaskRepository): TaskController {
        return TaskController(taskRepository)
    }
}