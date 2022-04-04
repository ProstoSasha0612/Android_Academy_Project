package com.projectapp.moviesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectapp.moviesapp.domain.model.Movie
import com.projectapp.moviesapp.domain.usecases.LoadMoviesUseCase
import kotlinx.coroutines.launch

class MoviesListViewModel(private val loadMoviesUseCase: LoadMoviesUseCase) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> get() = _moviesLiveData

    init {
        viewModelScope.launch {
            _moviesLiveData.value = loadMoviesUseCase.execute()
        }
    }

}