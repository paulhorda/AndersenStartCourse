package com.example.homework1.ui.controller

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.homework1.data.model.Task
import com.example.homework1.data.repository.TaskRepository
import com.example.homework1.ui.adapter.TaskAdapter

class TaskController(private val taskRepository: TaskRepository) {
    val adapter = TaskAdapter()

    fun getTasksLiveData(projectId: Long): LiveData<List<Task>>{
        return taskRepository.getTasks(projectId).asLiveData()
    }
    suspend fun saveTask(projectId: Long, taskName: String){
        taskRepository.saveTask(Task(0, taskName, projectId, 0.0))
    }

    suspend fun getTask(taskId: Long):Task{
        return taskRepository.getTask(taskId)
    }

    suspend fun updateTask(taskId: Long, timeToAdd: Double){
        val task = getTask(taskId)
        task.time += timeToAdd
        taskRepository.updateTask(task)
    }
}