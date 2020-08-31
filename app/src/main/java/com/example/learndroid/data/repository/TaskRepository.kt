package com.example.learndroid.data.repository

import androidx.lifecycle.LiveData
import com.example.learndroid.data.dao.TaskDao
import com.example.learndroid.data.entity.Task

class TaskRepository(private val taskDao: TaskDao) {
    val allWords: LiveData<List<Task>> = taskDao.getAll()

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

}