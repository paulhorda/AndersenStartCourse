package com.example.homework1.data.repository

import com.example.homework1.data.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun saveTask(task: Task)
    fun getTasks(projectId: Long): Flow<List<Task>>

    suspend fun getTask(taskId:Long): Task

    suspend fun updateTask(task: Task)
}