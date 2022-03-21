package com.projectapp.moviesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.projectapp.moviesapp.data.Movie
import com.projectapp.moviesapp.data.MovieDataSource
import com.projectapp.moviesapp.data.MoviesAdapter
import com.projectapp.moviesapp.databinding.FragmentMoviesListBinding

class FragmentMoviesList : Fragment() {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val adapter: MoviesAdapter? = null

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
//        binding.filmItem.viewHolderMovie.setOnClickListener {
//            openMovieDetailsFragment()
//        }
        val movieOnClickListener = object : MoviesAdapter.OnClickListener {
            override fun onClick(movie: Movie) {
                openMovieDetailsFragment(movie)
            }
        }
        with(binding.rvMoviesList) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = MoviesAdapter(MovieDataSource.getMovies(), resources, movieOnClickListener)
        }
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
