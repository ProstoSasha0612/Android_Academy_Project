package com.projectapp.moviesapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.data.model.Movie
import com.projectapp.moviesapp.domain.usecases.movielist.SaveGenresToDbUSeCase
import com.projectapp.moviesapp.domain.usecases.movielist.LoadMoviesUseCase
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val loadMoviesUseCase: LoadMoviesUseCase,
    private val saveGenresToDbUSeCase: SaveGenresToDbUSeCase
) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> get() = _moviesLiveData

    init {
        viewModelScope.launch {
            saveGenresToDbUSeCase()
//            _moviesLiveData.value = loadMoviesUseCase(1)
            try {
                val res = loadMoviesUseCase(1)
                _moviesLiveData.value = res
                res.forEach {
                    Log.d("RETROFIT", it.toString())
                }
                Log.d("RETROFIT", loadMoviesUseCase(1).toString())
            } catch (e: Exception) {
                Log.d("RETROFIT", e.stackTraceToString())
            }
        }
    }

}