package com.projectapp.moviesapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projectapp.moviesapp.domain.model.Movie
import com.projectapp.moviesapp.domain.usecases.moviedetails.GetGenresTextUseCase
import com.projectapp.moviesapp.presentation.viewmodel.MovieDetailsViewModel
import com.projectapp.moviesapp.presentation.viewmodel.MoviesListViewModel

class MovieDetailsViewModelFactory(private val movie: Movie?) : ViewModelProvider.Factory {

    private val getGenresTextUseCase = GetGenresTextUseCase()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(
            movie = movie,
            getGenresTextUseCase = getGenresTextUseCase
        ) as T
    }
}