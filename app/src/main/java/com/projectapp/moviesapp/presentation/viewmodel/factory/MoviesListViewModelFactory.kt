package com.projectapp.moviesapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projectapp.moviesapp.data.repository.MovieRepositoryImpl
import com.projectapp.moviesapp.domain.usecases.movielist.LoadGenresToDbUseCase
import com.projectapp.moviesapp.domain.usecases.movielist.LoadMoviesUseCase
import com.projectapp.moviesapp.presentation.viewmodel.MoviesListViewModel

class MoviesListViewModelFactory : ViewModelProvider.Factory {

    private val movieRepository = MovieRepositoryImpl.get()

    private val loadMoviesUseCase = LoadMoviesUseCase(movieRepository = movieRepository)
    private val loadGenresToDbUSeCase = LoadGenresToDbUseCase(movieRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesListViewModel(
            loadMoviesUseCase = loadMoviesUseCase,
            loadGenresToDbUSeCase = loadGenresToDbUSeCase
        ) as T
    }

}
