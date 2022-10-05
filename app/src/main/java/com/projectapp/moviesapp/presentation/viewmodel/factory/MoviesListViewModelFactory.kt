package com.projectapp.moviesapp.presentation.viewmodel.factory

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projectapp.moviesapp.data.repository.MovieRepositoryImpl
import com.projectapp.moviesapp.domain.logic.InternetStatusChecker
import com.projectapp.moviesapp.domain.usecases.movielist.LoadAndSaveGenresToDbUseCase
import com.projectapp.moviesapp.domain.usecases.movielist.LoadMoviesUseCase
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType
import com.projectapp.moviesapp.presentation.viewmodel.MoviesListViewModel

class MoviesListViewModelFactory(movieType: MovieType, connectivityManager: ConnectivityManager) :
    ViewModelProvider.Factory {

    private val movieRepository = MovieRepositoryImpl.get()
    private val internetStatusChecker = object : InternetStatusChecker {
        override fun isInternetOn() = checkInternet(connectivityManager)
    }

    private val loadMoviesUseCase =
        LoadMoviesUseCase(movieRepository = movieRepository,
            movieType = movieType,
            internetStatusChecker = internetStatusChecker)
    private val loadAndSaveGenresToDbUSeCase =
        LoadAndSaveGenresToDbUseCase(movieRepository, internetStatusChecker = internetStatusChecker)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesListViewModel(
            loadMoviesUseCase = loadMoviesUseCase,
            loadAndSaveGenresToDbUSeCase = loadAndSaveGenresToDbUSeCase
        ) as T
    }

    //TODO when implementing dagger it will be provided from component
    private fun checkInternet(connectivityManager: ConnectivityManager): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    ?: return false
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    }

}
