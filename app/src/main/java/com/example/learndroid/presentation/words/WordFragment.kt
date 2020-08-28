package com.example.learndroid.presentation.words

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learndroid.R
import com.example.learndroid.adapters.SwipeToDeleteCallback
import com.example.learndroid.adapters.WordsListAdapter
import com.example.learndroid.data.entity.Word
import com.example.learndroid.databinding.WordsListFragmentBinding
import kotlinx.android.synthetic.main.words_list_fragment.*


class WordFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(WordViewModel::class.java)
    }
    private val wordsAdapter by lazy {
        WordsListAdapter()
    }

    private var _binding: WordsListFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.state.observe(this, Observer {
            render(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WordsListFragmentBinding.inflate(inflater, container, false)

        binding.recycler.apply{
            setHasFixedSize(true)
            adapter = wordsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(wordsAdapter))
        itemTouchHelper.attachToRecyclerView(binding.recycler)

        wordsAdapter.setWords(listOf(Word("Example"), Word("Words")))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            if (TextUtils.isEmpty(edit_text.text)) {
                Toast.makeText(context, getString(R.string.toast_not_empty), Toast.LENGTH_SHORT).show()
            } else {
                val word : Word = Word(edit_text.text.toString().trim())
                edit_text.text.clear()
                viewModel.dispatch(WordListAction.AddButtonClicked(word))
            }
        }

        binding.clearButton.setOnClickListener {
            viewModel.dispatch(WordListAction.ClearButtonPressed)
        }
    }

    private fun render(state: WordListState) {
        when (state) {
            is WordListState.AddedWord -> wordsAdapter.insertWord(state.word)
            is WordListState.ChangedList -> wordsAdapter.setWords(state.wordList)
            is WordListState.RemovedWord -> wordsAdapter.removeWord(state.position)
            is WordListState.RemovedList -> wordsAdapter.setWords(emptyList())
        }
    }
}
