package com.projectapp.moviesapp.presentation.viewpager

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType
import com.projectapp.moviesapp.presentation.ui.FragmentMoviesList

class PagerFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentCount = MovieType.values().size
    override fun getItemCount(): Int {
        return fragmentCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentMoviesList.newInstance(MovieType.POPULAR)
            1 -> FragmentMoviesList.newInstance(MovieType.TOP_RATED)
            2 -> FragmentMoviesList.newInstance(MovieType.UPCOMING)
//            3 -> FragmentMoviesList.newInstance(MovieType.UPCOMING)
            else -> throw IllegalStateException()
        }
    }

}
