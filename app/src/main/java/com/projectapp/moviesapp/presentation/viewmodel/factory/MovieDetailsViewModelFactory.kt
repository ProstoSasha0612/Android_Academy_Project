package com.projectapp.moviesapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projectapp.moviesapp.data.model.UiMovie
import com.projectapp.moviesapp.presentation.viewmodel.MovieDetailsViewModel

class MovieDetailsViewModelFactory(private val uiMovie: UiMovie) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(uiMovie = uiMovie) as T
    }
}