package com.projectapp.moviesapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectapp.moviesapp.domain.model.Movie
import com.projectapp.moviesapp.domain.usecases.movielist.LoadMoviesTestUseCase
import com.projectapp.moviesapp.domain.usecases.movielist.LoadMoviesUseCase
import kotlinx.coroutines.launch

class MoviesListViewModel(private val loadMoviesUseCase: LoadMoviesUseCase,private val loadMoviesTestUseCase:LoadMoviesTestUseCase) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> get() = _moviesLiveData

    init {
        viewModelScope.launch {
            _moviesLiveData.value = loadMoviesUseCase.execute()
            try {
                val res = loadMoviesTestUseCase.execute()
                res.forEach{
                    Log.d("RETROFIT",it.toString())
                }
                Log.d("RETROFIT",loadMoviesTestUseCase.execute().toString())
            }
            catch (e:Exception){
                Log.d("RETROFIT", e.stackTraceToString())
            }
        }
    }

}