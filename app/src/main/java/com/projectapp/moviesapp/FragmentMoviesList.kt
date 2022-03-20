package com.projectapp.moviesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.projectapp.moviesapp.databinding.MoviesListFragmentBinding

class FragmentMoviesList : Fragment() {

    private var _binding: MoviesListFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MoviesListFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    companion object {
        fun newInstance() = FragmentMoviesList()
    }
}
