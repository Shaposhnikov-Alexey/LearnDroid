package com.example.learndroid.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.learndroid.data.entity.Task

@Dao
interface TaskDao {
    @Insert
    fun insert(task: Task)

    @Query("SELECT * FROM task_table ORDER BY task ASC")
    fun getAll() : LiveData<List<Task>>

}