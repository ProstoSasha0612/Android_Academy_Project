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
    private var viewPager: ViewPager2? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMoviesListPagerBinding.inflate(layoutInflater, container, false)

        viewPager = binding.viewpager
        viewPager?.adapter =
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewPager = binding.viewpager
//        viewPager?.adapter = PagerFragmentAdapter(childFragmentManager,viewLifecycleOwner.lifecycle)

//        mediator = TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
//            tab.text = MovieType.values()[position].typeName
//        }
//        mediator?.attach()
    }

    override fun onDestroyView() {
        Log.d("MYTAG", "onDestroyView called")
        mediator?.detach()
        mediator = null
        binding.viewpager.adapter = null
        viewPager?.adapter = null
        viewPager = null
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("MYTAG", "onDestroy called")
        mediator?.detach()
        mediator = null
        binding.viewpager.adapter = null
        viewPager?.adapter = null
        viewPager = null
        _binding = null
        super.onDestroy()
    }


    companion object {
        @JvmStatic
        fun newInstance() = FragmentMoviesListPager()
    }
}
