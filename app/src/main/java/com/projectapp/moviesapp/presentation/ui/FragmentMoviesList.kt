package com.projectapp.moviesapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.projectapp.moviesapp.R
import com.projectapp.moviesapp.data.model.Movie
import com.projectapp.moviesapp.databinding.FragmentMoviesListBinding
import com.projectapp.moviesapp.presentation.recyclerview.ItemOffsetDecoration
import com.projectapp.moviesapp.presentation.recyclerview.MovieFooterAdapter
import com.projectapp.moviesapp.presentation.recyclerview.MoviesAdapter
import com.projectapp.moviesapp.presentation.viewmodel.MoviesListViewModel
import com.projectapp.moviesapp.presentation.viewmodel.factory.MoviesListViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FragmentMoviesList : Fragment() {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var moviesAdapter: MoviesAdapter? = null

    private val vm by lazy {
        ViewModelProvider(
            this,
            MoviesListViewModelFactory()
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
        Log.d("MYTAG", "adapter ready")
        lifecycleScope.launch {
            launch {
                setUpFlowDataObserving()
            }
            launch {
                setUpLoadStateFlowObserving()
            }
        }
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun setUpAdapter() {
        binding.rvMoviesList.layoutManager = GridLayoutManager(context, 2)
        moviesAdapter = MoviesAdapter(resources, movieOnClickListener)

        val concatAdapter = moviesAdapter?.withLoadStateFooter(
            footer = MovieFooterAdapter {
                Toast.makeText(
                    this.context,
                    "Retry clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )

        binding.rvMoviesList.adapter = concatAdapter
        binding.rvMoviesList.addItemDecoration(ItemOffsetDecoration(12))
    }

    private suspend fun setUpFlowDataObserving() {
        vm.movieListData.collect {
            moviesAdapter?.submitData(it)
        }
    }

    private suspend fun setUpLoadStateFlowObserving() {
        moviesAdapter?.let {
            it.loadStateFlow.collectLatest { loadState ->
                if (loadState.refresh == LoadState.Loading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
                Log.d("MYTAGS", "Adapter loadState is $loadState")
            }
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
