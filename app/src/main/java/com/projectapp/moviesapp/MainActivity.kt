package com.projectapp.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.projectapp.moviesapp.databinding.ActivityMainBinding

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

    private fun openMoviesListFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,FragmentMoviesList.newInstance())
            .commit()

    }
}