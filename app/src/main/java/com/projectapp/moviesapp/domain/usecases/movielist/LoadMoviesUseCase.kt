package com.projectapp.moviesapp.domain.usecases.movielist

import android.util.Log
import com.projectapp.moviesapp.data.model.UiMovie
import com.projectapp.moviesapp.data.model.mapToDataMoviesList
import com.projectapp.moviesapp.domain.logic.InternetStatusChecker
import com.projectapp.moviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadMoviesUseCase(
    private val movieRepository: MovieRepository,
    private val movieType: MovieType,
    private val internetStatusChecker: InternetStatusChecker,
) {

    //if internet is on -> clear movies table
//    private var needClearMovieTable = internetStatusChecker.isInternetOn()


    suspend operator fun invoke(pageNumber: Int): List<UiMovie>/* = withContext(Dispatchers.IO)*/ {
        Log.d("MYTAG", "loadMoviesUSeCase calling")

        if (internetStatusChecker.isInternetOn()) {
            val moviesFromNetwork = movieRepository.loadMovies(pageNumber, movieType)
            Log.d("MYTAG",
                "movies list downloaded from network/api with PAGE number = $pageNumber")
            Log.d("MYTAG", "movies list downloaded from network/api = $moviesFromNetwork")
            val dataMoviesList = moviesFromNetwork.mapToDataMoviesList(movieType)
            Log.d("MYTAG", "JsonMovie mapped to DataMovie")
            Log.d("MYTAG", "mapped MoviesList: $dataMoviesList")
            Log.d("MYTAG", "mapped MoviesList size: ${dataMoviesList.size}")
            movieRepository.saveMoviesToDb(dataMoviesList)
        }
        val moviesFromDb = movieRepository.getMoviesFromDb(pageNumber, movieType)
        return movieRepository.mapMovieListToUiMovieList(moviesFromDb)
    }
}

