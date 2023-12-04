package com.example.homework1.data.repository.impl

import com.example.homework1.data.dao.ProjectDao
import com.example.homework1.data.model.Project
import com.example.homework1.data.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepositoryImpl @Inject constructor(private val projectDao: ProjectDao) :
    ProjectRepository {
    override suspend fun saveProject(project: Project) {
        projectDao.saveProject(project)
    }

    override fun getProjects(): Flow<List<Project>> {
        return projectDao.getProjects()
    }
}