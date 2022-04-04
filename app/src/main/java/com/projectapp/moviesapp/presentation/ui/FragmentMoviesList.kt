package com.projectapp.moviesapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.projectapp.moviesapp.R
import com.projectapp.moviesapp.databinding.FragmentMoviesListBinding
import com.projectapp.moviesapp.domain.model.Movie
import com.projectapp.moviesapp.presentation.recyclerview.MoviesAdapter
import com.projectapp.moviesapp.presentation.viewmodel.MoviesListViewModel
import com.projectapp.moviesapp.presentation.viewmodel.factory.MoviesListViewModelFactory

class FragmentMoviesList : Fragment() {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val vm by lazy {
        ViewModelProvider(
            this,
            MoviesListViewModelFactory(requireContext())
        ).get(MoviesListViewModel::class.java)
    }

    val movieOnClickListener by lazy {
        object : MoviesAdapter.OnClickListener {
            override fun onClick(movie: Movie) {
                openMovieDetailsFragment(movie)
            }
        }
    }
    private val moviesAdapter by lazy {
        MoviesAdapter(
            emptyList(),
            resources,
            movieOnClickListener
        )
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

    private fun setUpAdapter() {
        binding.rvMoviesList.layoutManager = GridLayoutManager(context, 2)
        binding.rvMoviesList.adapter = moviesAdapter
    }

    private fun updateAdapter(moviesList: List<Movie>) {
        //TODO придумать как здесь сделать получше.
        //TODO как вариант сделать наследование от listAdapter и использовать что-то вроде notifyItemInserted()
        binding.rvMoviesList.adapter = MoviesAdapter(moviesList, resources, movieOnClickListener)
        binding.rvMoviesList.adapter?.notifyDataSetChanged()
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
