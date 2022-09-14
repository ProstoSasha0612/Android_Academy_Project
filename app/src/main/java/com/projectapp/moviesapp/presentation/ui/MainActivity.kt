package com.projectapp.moviesapp.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.projectapp.moviesapp.R
import com.projectapp.moviesapp.databinding.ActivityMainBinding
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(savedInstanceState == null){
            openMoviesListFragment()
        }
    }

//    private fun openMoviesListFragment(){
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container,FragmentMoviesListPager.newInstance())
//            .addToBackStack("ViewPagerFragment")
////            .replace(R.id.fragment_container, FragmentMoviesList.newInstance(MovieType.POPULAR))
//            .commit()
//
//    }

    private fun openMoviesListFragment(){
        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.action_navHostFragment_to_fragmentMoviesListPager)
    }
}