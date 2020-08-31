package com.example.learndroid.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @NonNull
    @ColumnInfo(name = "task")
    val taskName: String,
    @ColumnInfo(name = "Completed", defaultValue = "Nah" )
    val isDone: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0) {
}