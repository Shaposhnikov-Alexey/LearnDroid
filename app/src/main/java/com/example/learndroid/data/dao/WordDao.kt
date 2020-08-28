package com.example.learndroid.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.learndroid.data.entity.Word

@Dao
interface WordDao {
    @Insert
    fun insert(word: Word)

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAll() : LiveData<List<Word>>

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Query("DELETE FROM word_table WHERE id = :position")
    suspend fun delete(position: Int)
}