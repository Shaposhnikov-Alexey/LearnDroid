package com.example.learndroid.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(
    @NonNull
    @ColumnInfo(name = "word")
    val word: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0) {
}