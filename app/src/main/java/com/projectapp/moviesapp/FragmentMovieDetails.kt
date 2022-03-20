package com.projectapp.moviesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.projectapp.moviesapp.databinding.FragmentMovieDetailsBinding

class FragmentMovieDetails : Fragment() {


    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, true)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMoviesList()

    }
}