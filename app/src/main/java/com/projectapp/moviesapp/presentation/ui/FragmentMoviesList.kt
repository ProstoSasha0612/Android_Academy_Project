package com.projectapp.moviesapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.projectapp.moviesapp.R
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.databinding.FragmentMoviesListBinding
import com.projectapp.moviesapp.domain.model.Movie
import com.projectapp.moviesapp.presentation.recyclerview.MoviesAdapter
import com.projectapp.moviesapp.presentation.viewmodel.MoviesListViewModel
import com.projectapp.moviesapp.presentation.viewmodel.factory.MoviesListViewModelFactory

class FragmentMoviesList : Fragment() {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var moviesAdapter: MoviesAdapter? = null

    private val vm by lazy {
        ViewModelProvider(
            this,
            MoviesListViewModelFactory(requireActivity().applicationContext)
        )[MoviesListViewModel::class.java]
    }
    private val movieOnClickListener by lazy {
        object : MoviesAdapter.OnClickListener {
            override fun onClick(movie: Movie) {
                openMovieDetailsFragment(movie)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()

        vm.moviesLiveData.observe(viewLifecycleOwner, this::updateAdapter)

    }




    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun setUpAdapter() {
        moviesAdapter = MoviesAdapter(resources, movieOnClickListener)
        binding.rvMoviesList.layoutManager = GridLayoutManager(context, 2)
        binding.rvMoviesList.adapter = moviesAdapter
    }

    private fun updateAdapter(moviesList: List<JsonMovie>) {
        Log.d("MYTAG", "All is ok, movies list size = ${moviesList.size}")
        Log.d("MYTAG", "last movie .toSting = ${moviesList.last().title}")
//        moviesAdapter?.submitList(moviesList)
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
