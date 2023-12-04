package com.example.homework1.ui.controller

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.homework1.data.model.Project
import com.example.homework1.data.repository.ProjectRepository
import com.example.homework1.ui.adapter.ProjectAdapter


class ProjectController(private val projectRepository: ProjectRepository) {

    val projects: LiveData<List<Project>> = projectRepository.getProjects().asLiveData()
    val adapter = ProjectAdapter()

    suspend fun saveProject(project: String) {
        projectRepository.saveProject(Project(0, project))
    }
}