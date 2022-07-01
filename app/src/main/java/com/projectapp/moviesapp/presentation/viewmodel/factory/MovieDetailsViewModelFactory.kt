package com.projectapp.moviesapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projectapp.moviesapp.data.model.Movie
import com.projectapp.moviesapp.presentation.viewmodel.MovieDetailsViewModel

class MovieDetailsViewModelFactory(private val movie: Movie?) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(movie = movie) as T
    }
}