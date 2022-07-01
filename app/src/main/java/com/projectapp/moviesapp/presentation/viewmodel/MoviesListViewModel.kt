package com.projectapp.moviesapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.domain.usecases.movielist.FillGenreStorageUseCase
import com.projectapp.moviesapp.domain.usecases.movielist.LoadMoviesUseCase
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val loadMoviesUseCase: LoadMoviesUseCase,
    private val fillGenreStorageUseCase: FillGenreStorageUseCase
) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<JsonMovie>>()
    val moviesLiveData: LiveData<List<JsonMovie>> get() = _moviesLiveData

    init {
        viewModelScope.launch {
            fillGenreStorageUseCase()
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