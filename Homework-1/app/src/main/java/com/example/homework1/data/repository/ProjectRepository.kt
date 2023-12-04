package com.example.homework1.data.repository

import com.example.homework1.data.model.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    suspend fun saveProject(project: Project)
    fun getProjects(): Flow<List<Project>>

}