package com.example.homework1.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.homework1.data.model.Project
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Insert
    suspend fun saveProject(project: Project)

    @Query("SELECT * FROM projects")
    fun getProjects(): Flow<List<Project>>
}