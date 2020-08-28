package com.example.learndroid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learndroid.data.entity.Word
import com.example.learndroid.databinding.RecyclerviewItemBinding

class WordsListAdapter : RecyclerView.Adapter<WordsListAdapter.WordsViewHolder>() {
    private var words : MutableList<Word> = emptyList<Word>().toMutableList()

    class WordsViewHolder(
        val binding: RecyclerviewItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WordsViewHolder(
            RecyclerviewItemBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        val current = words[position]
        holder.binding.textView.text = current.word
    }

    internal fun setWords(words: List<Word>) {
        this.words = words.toMutableList()
        notifyDataSetChanged()
    }

    internal fun insertWord(word: Word) {
        words.add(word)
        notifyItemInserted(words.size - 1)
    }


    override fun getItemCount(): Int = words.size
    fun removeWord(position: Int) {
        words.removeAt(position)
        notifyItemRemoved(position)
    }
}

