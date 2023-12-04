package com.example.homework1.data.database

import androidx.annotation.Keep
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework1.data.dao.ProjectDao
import com.example.homework1.data.dao.TaskDao
import com.example.homework1.data.model.Project
import com.example.homework1.data.model.Task

@Database(entities = [Task::class, Project::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun taskDao(): TaskDao
}