package com.projectapp.moviesapp.presentation.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projectapp.moviesapp.data.repository.JsonMovieRepositoryImpl
import com.projectapp.moviesapp.data.repository.MovieRepositoryImpl
import com.projectapp.moviesapp.domain.usecases.LoadMoviesUseCase
import com.projectapp.moviesapp.presentation.viewmodel.MoviesListViewModel

class MoviesListViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val jsonMovieRepository = JsonMovieRepositoryImpl(context = context)
    private val movieRepository = MovieRepositoryImpl(jsonMovieRepository = jsonMovieRepository)
    private val loadMoviesUseCase = LoadMoviesUseCase(repository = movieRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesListViewModel(loadMoviesUseCase = loadMoviesUseCase) as T
    }
}