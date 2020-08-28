package com.example.learndroid.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learndroid.R
import com.example.learndroid.data.entity.Word

class WordsListAdapter : RecyclerView.Adapter<WordsListAdapter.WordsViewHolder>() {
    private var words : MutableList<Word> = emptyList<Word>().toMutableList()

    class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        val current = words[position]
        holder.wordItemView.text = current.word
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