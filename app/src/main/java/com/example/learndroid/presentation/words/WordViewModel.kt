package com.example.learndroid.presentation.words

import android.app.Application
import androidx.lifecycle.*
import com.example.learndroid.data.db.AppRoomDatabase
import com.example.learndroid.data.entity.Word
import com.example.learndroid.data.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WordRepository
    private var allWords: List<Word>
    val state: MutableLiveData<WordListState> = MutableLiveData()

    init {
        val wordsDao = AppRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords.value ?: emptyList()
    }

    fun dispatch(action: WordListAction) {
        when (action) {
            is WordListAction.WordSwiped -> {
                viewModelScope.launch {
                    repository.remove(action.position)
                }
                updateState(WordListState.RemovedWord(action.position))
            }
            is WordListAction.AddButtonClicked -> viewModelScope.launch(Dispatchers.IO) {
                repository.insert(action.word)
                updateState(WordListState.AddedWord(action.word))
            }
            is WordListAction.Refreshed -> {
                allWords = repository.allWords.value ?: emptyList()
                updateState(WordListState.ChangedList(allWords))
            }
            is WordListAction.ClearButtonPressed -> {
                viewModelScope.launch {
                   repository.removeAll()
                }
                updateState(WordListState.ChangedList(allWords))
            }
        }
    }

    private fun updateState(_state: WordListState) {
        viewModelScope.launch(Dispatchers.Main) {
            state.value = _state
        }
    }
}

