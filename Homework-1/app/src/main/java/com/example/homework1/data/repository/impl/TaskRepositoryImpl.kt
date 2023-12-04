package com.example.homework1.data.repository.impl

import com.example.homework1.data.dao.TaskDao
import com.example.homework1.data.model.Task
import com.example.homework1.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao) : TaskRepository {
    override suspend fun saveTask(task: Task) {
        taskDao.saveTask(task)
    }

    override fun getTasks(projectId: Long): Flow<List<Task>> {
        return taskDao.getTasks(projectId)
    }

    override suspend fun getTask(taskId: Long): Task {
        return taskDao.getTask(taskId)
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }
}