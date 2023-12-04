package com.example.homework1.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.homework1.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun saveTask(task: Task)
    @Query("SELECT * FROM tasks WHERE projectId = :projectId")
    fun getTasks(projectId: Long): Flow<List<Task>>
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTask(taskId:Long): Task
    @Update
    suspend fun updateTask(task: Task)
}