package com.projectapp.moviesapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.projectapp.moviesapp.R
import com.projectapp.moviesapp.data.model.UiMovie
import com.projectapp.moviesapp.databinding.FragmentMoviesListBinding
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType
import com.projectapp.moviesapp.presentation.recyclerview.ItemOffsetDecoration
import com.projectapp.moviesapp.presentation.recyclerview.MovieFooterAdapter
import com.projectapp.moviesapp.presentation.recyclerview.FooterSpanSizeLookup
import com.projectapp.moviesapp.presentation.recyclerview.MoviesAdapter
import com.projectapp.moviesapp.presentation.viewmodel.MoviesListViewModel
import com.projectapp.moviesapp.presentation.viewmodel.factory.MoviesListViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FragmentMoviesList : Fragment() {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var moviesAdapter: MoviesAdapter? = null
    private val movieType: MovieType? by lazy { arguments?.getSerializable(MOVIE_TYPE_KEY) as MovieType? }

    private val vm by lazy {
        ViewModelProvider(
            this,
            MoviesListViewModelFactory(movieType ?: MovieType.POPULAR)
        )[MoviesListViewModel::class.java]
    }
    private val movieOnClickListener by lazy {
        object : MoviesAdapter.OnClickListener {
            override fun onClick(uiMovie: UiMovie) {
                openMovieDetailsFragment(uiMovie)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMoviesListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()
        setUpOnClickListeners()
        setUpFlowDataObserving()

//        setUpLoadStateFlowObserving()

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setUpAdapter() {
        moviesAdapter = MoviesAdapter(resources, movieOnClickListener)

        val concatAdapter = moviesAdapter?.withLoadStateFooter(
            footer = MovieFooterAdapter(retry = {
                moviesAdapter?.retry()
            })
        )
        val layoutManager = GridLayoutManager(context, 2).apply {
            spanSizeLookup =
                FooterSpanSizeLookup(concatAdapter, spanCount, MoviesAdapter.FOOTER_VIEW_TYPE)
        }
        binding.rvMoviesList.layoutManager = layoutManager
        binding.rvMoviesList.adapter = concatAdapter
        binding.rvMoviesList.addItemDecoration(ItemOffsetDecoration())
    }

    private fun setUpOnClickListeners() {
        binding.retryBtn.setOnClickListener {
            moviesAdapter?.retry()
        }
    }

    private fun setUpFlowDataObserving() {
        lifecycleScope.launch{
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.movieListData.collect {
                    moviesAdapter?.submitData(it)
                }
            }
        }
    }

    private fun setUpLoadStateFlowObserving() {
        lifecycleScope.launch {
            moviesAdapter?.let {
                it.loadStateFlow.collectLatest { loadState ->
                    binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                    binding.retryBtn.isVisible = loadState.source.refresh is LoadState.Error
                    binding.tvErrorText.isVisible = loadState.source.refresh is LoadState.Error
                    Log.d("MYTAGS", "Adapter loadState is $loadState")
                }
            }
        }
    }

    private fun openMovieDetailsFragment(uiMovie: UiMovie) {
        findNavController().navigate(
            resId = R.id.fragmentMovieDetails,
            args = bundleOf(
                Pair(FragmentMovieDetails.KEY_MOVIE, uiMovie)
            )
        )
    }

    companion object {
        fun newInstance(movieType: MovieType): FragmentMoviesList {
            return FragmentMoviesList().apply {
                arguments = bundleOf().also {
                    it.putSerializable(MOVIE_TYPE_KEY, movieType)
                }
            }
        }

        const val MOVIE_TYPE_KEY = "movie type key"
    }
}
