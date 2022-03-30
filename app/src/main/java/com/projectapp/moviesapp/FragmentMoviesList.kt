package com.projectapp.moviesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.projectapp.moviesapp.data.JsonMovieRepository
import com.projectapp.moviesapp.data.model.Movie
import com.projectapp.moviesapp.my_data.MovieDataSource
import com.projectapp.moviesapp.my_data.MoviesAdapter
import com.projectapp.moviesapp.databinding.FragmentMoviesListBinding
import kotlinx.coroutines.*

class FragmentMoviesList : Fragment() {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = requireNotNull(_binding)

    //vieModelValues


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieOnClickListener = object : MoviesAdapter.OnClickListener {
            override fun onClick(movie: Movie) {
                openMovieDetailsFragment(movie)
            }
        }
        var moviesList: List<Movie>? = null
        CoroutineScope(Dispatchers.IO).launch {
            moviesList = JsonMovieRepository(requireContext()).loadMovies()
                with(binding.rvMoviesList) {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = MoviesAdapter(moviesList?: emptyList(), resources, movieOnClickListener)
                }
            Log.d("LOG1","before delay")
            delay(5000)
            Log.d("LOG1","after delay")
        }

        Log.d("LOG1","out from coroutineScope")
    }

    private fun openMovieDetailsFragment(movie: Movie) {
        parentFragmentManager.beginTransaction()
            .addToBackStack("MoviesDetailsFragment")
            .add(R.id.fragment_container, FragmentMovieDetails.newInstance(movie))
            .commit()
    }

    companion object {
        fun newInstance() = FragmentMoviesList()
    }
}
