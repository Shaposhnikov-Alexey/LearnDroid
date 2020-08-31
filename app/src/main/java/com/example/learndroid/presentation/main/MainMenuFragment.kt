package com.example.learndroid.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learndroid.R
import com.example.learndroid.adapters.TasksAdapter
import com.example.learndroid.data.entity.Task
import com.example.learndroid.databinding.MainFragmentBinding
import com.example.learndroid.presentation.words.WordFragment

class MainMenuFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding
        get() = _binding!!
    private val tasksAdapter by lazy {
        TasksAdapter { position ->
            renderTask(position)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        binding.tasksRecycler.apply{
            setHasFixedSize(true)
            adapter = tasksAdapter
            layoutManager = LinearLayoutManager(context)
        }

        tasksAdapter.setTasks(listOf(
            Task(getString(R.string.task_words), true),
            Task(getString(R.string.task_ar), false),
            Task(getString(R.string.task_http), false),
            Task(getString(R.string.task_coroutines), false),
            Task(getString(R.string.task_camera), false)
        ))

        return binding.root
    }

    private fun renderTask(position: Int) {
        val taskName = tasksAdapter.getTaskInfo(position).taskName
        when (taskName) {
            getString(R.string.task_words) -> parentFragmentManager.commit {
                replace(R.id.container, WordFragment()).addToBackStack("Main screen")
            }
        }
    }
}

