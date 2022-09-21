package com.projectapp.moviesapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projectapp.moviesapp.data.repository.MovieRepositoryImpl
import com.projectapp.moviesapp.domain.usecases.movielist.LoadAndSaveGenresToDbUseCase
import com.projectapp.moviesapp.domain.usecases.movielist.LoadMoviesUseCase
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType
import com.projectapp.moviesapp.presentation.viewmodel.MoviesListViewModel

class MoviesListViewModelFactory(movieType: MovieType) : ViewModelProvider.Factory {

    private val movieRepository = MovieRepositoryImpl.get()

    private val loadMoviesUseCase =
        LoadMoviesUseCase(movieRepository = movieRepository, movieType = movieType)
    private val loadAndSaveGenresToDbUSeCase = LoadAndSaveGenresToDbUseCase(movieRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesListViewModel(
            loadMoviesUseCase = loadMoviesUseCase,
            loadAndSaveGenresToDbUSeCase = loadAndSaveGenresToDbUSeCase
        ) as T
    }

}
