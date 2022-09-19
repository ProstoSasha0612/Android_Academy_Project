package com.projectapp.moviesapp.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.projectapp.moviesapp.R
import com.projectapp.moviesapp.databinding.FragmentMoviesListPagerBinding
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType
import com.projectapp.moviesapp.presentation.viewpager.PagerFragmentAdapter

class FragmentMoviesListPager : Fragment() {

    private var _binding: FragmentMoviesListPagerBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var mediator: TabLayoutMediator? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMoviesListPagerBinding.inflate(layoutInflater, container, false)

        setUpTabLayoutMediator()

        return binding.root
    }

    private fun setUpTabLayoutMediator() {
        binding.viewpager.adapter =
            PagerFragmentAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)

        mediator = TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = when (MovieType.values()[position]) {
                MovieType.POPULAR -> resources.getString(R.string.movie_type_popular)
                MovieType.TOP_RATED -> resources.getString(R.string.movie_type_top_rated)
                MovieType.LATEST -> resources.getString(R.string.movie_type_latest)
                MovieType.UPCOMING -> resources.getString(R.string.movie_type_upcoming)
            }
        }
        mediator?.attach()
    }

    override fun onDestroyView() {
        Log.d("MYTAG", "onDestroyView called")
        mediator?.detach()
        mediator = null
        _binding = null
        super.onDestroyView()
    }


    companion object {
        @JvmStatic
        fun newInstance() = FragmentMoviesListPager()
    }
}
