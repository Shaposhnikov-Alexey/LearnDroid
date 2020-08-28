package com.example.learndroid.data.repository

import androidx.lifecycle.LiveData
import com.example.learndroid.data.dao.WordDao
import com.example.learndroid.data.entity.Word

class WordRepository(private val wordDao: WordDao) {
    val allWords: LiveData<List<Word>> = wordDao.getAll()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun remove(position: Int) {
        wordDao.delete(position)
    }

    suspend fun removeAll() {
        wordDao.deleteAll()
    }
}