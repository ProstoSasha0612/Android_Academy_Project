package com.projectapp.moviesapp.presentation.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projectapp.moviesapp.data.remotedata.RemoteDataSourceImpl
import com.projectapp.moviesapp.data.repository.MovieRepositoryImpl
import com.projectapp.moviesapp.domain.usecases.movielist.LoadMoviesTestUseCase
import com.projectapp.moviesapp.domain.usecases.movielist.LoadMoviesUseCase
import com.projectapp.moviesapp.presentation.viewmodel.MoviesListViewModel

class MoviesListViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val remoteDataSource = RemoteDataSourceImpl(context = context)
    private val movieRepository = MovieRepositoryImpl(remoteDataSource = remoteDataSource)
    private val loadMoviesUseCase = LoadMoviesUseCase(repository = movieRepository)
    private val loadMoviesTestUseCase = LoadMoviesTestUseCase(movieRepository = movieRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesListViewModel(loadMoviesUseCase = loadMoviesUseCase,loadMoviesTestUseCase = loadMoviesTestUseCase) as T
    }

}