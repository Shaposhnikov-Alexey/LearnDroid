package com.example.learndroid.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.findNavController
import com.example.learndroid.R
import com.example.learndroid.databinding.MainFragmentBinding
import com.example.learndroid.databinding.WordsListFragmentBinding
import com.example.learndroid.presentation.words.WordFragment

class MainMenuFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.MainToWordFragment)

            parentFragmentManager.commit {
                remove(this@MainMenuFragment)
                add(R.id.content, WordFragment())
            }
        }
    }
}
