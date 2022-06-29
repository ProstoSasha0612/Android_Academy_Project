package com.projectapp.moviesapp.presentation.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projectapp.moviesapp.data.datasource.local.LocalDataSourceImpl
import com.projectapp.moviesapp.data.datasource.remotedata.RemoteDataSourceImpl
import com.projectapp.moviesapp.data.datasource.sharedprefs.SharedPrefsDataSource
import com.projectapp.moviesapp.data.datasource.sharedprefs.SharedPrefsDataSourceImpl
import com.projectapp.moviesapp.data.repository.MovieRepositoryImpl
import com.projectapp.moviesapp.domain.usecases.movielist.FillGenreStorageUseCase
import com.projectapp.moviesapp.domain.usecases.movielist.LoadMoviesUseCase
import com.projectapp.moviesapp.presentation.viewmodel.MoviesListViewModel

class MoviesListViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val remoteDataSource = RemoteDataSourceImpl()
    private val localDataSource = LocalDataSourceImpl()
    private val sharedPrefsDataSource = SharedPrefsDataSourceImpl(context = context)

    private val movieRepository = MovieRepositoryImpl(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource,
        sharedPrefsDataSource = sharedPrefsDataSource
    )
    
    private val loadMoviesUseCase = LoadMoviesUseCase(movieRepository = movieRepository)
    private val fillGenreStorageUseCase = FillGenreStorageUseCase(movieRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesListViewModel(
            loadMoviesUseCase = loadMoviesUseCase,
            fillGenreStorageUseCase = fillGenreStorageUseCase
        ) as T
    }

}