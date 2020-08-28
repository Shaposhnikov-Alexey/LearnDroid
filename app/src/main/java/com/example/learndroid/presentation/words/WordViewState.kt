package com.example.learndroid.presentation.words

import com.example.learndroid.data.entity.Word

sealed class WordListAction {
    object Refreshed: WordListAction()
    object ClearButtonPressed: WordListAction()
    data class AddButtonClicked(val word: Word): WordListAction()
    data class WordSwiped(val position: Int): WordListAction()
}

sealed class WordListState() {
    object RemovedList: WordListState()
    data class AddedWord(val word: Word): WordListState()
    data class ChangedList(val wordList: List<Word>): WordListState()
    data class RemovedWord(val position: Int): WordListState()
}