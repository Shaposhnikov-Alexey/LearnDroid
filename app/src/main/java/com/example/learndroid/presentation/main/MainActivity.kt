package com.example.learndroid.presentation.main

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.*
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learndroid.R
import com.example.learndroid.adapters.WordsListAdapter
import com.example.learndroid.databinding.ActivityMainBinding

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.fab.setOnClickListener {
            Toast.makeText(
                this,
                "So, why did you click me? Feel proud, ain't ya?",
                Toast.LENGTH_SHORT
            ).show()

        }

        supportFragmentManager.commit {
            add(R.id.content, MainMenuFragment())
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
