package com.projectapp.moviesapp.domain.usecases.movielist

import android.util.Log
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.data.model.UiMovie
import com.projectapp.moviesapp.data.model.mapToUiMovie
import com.projectapp.moviesapp.domain.logic.InternetStatusChecker
import com.projectapp.moviesapp.domain.logic.TablesCleaner
import com.projectapp.moviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.*

class LoadMoviesUseCase(
    private val movieRepository: MovieRepository,
    private val movieType: MovieType,
    private val internetStatusChecker: InternetStatusChecker,
) {

    //if internet is on -> clear movies table
//    private var needClearMovieTable = internetStatusChecker.isInternetOn()


    suspend operator fun invoke(pageNumber: Int): List<UiMovie> = withContext(Dispatchers.Main) {
        Log.d("MYTAG", "loadMoviesUSeCase calling")
//        val res = movieRepository.loadMovies(pageNumber, movieType)
//        Log.d("MYTAG", "loadMoviesUSeCase result List = $res")
//        Log.d("MYTAG", "loadMoviesUSeCase result List size= ${res.size}")

//        if(needClearMovieTable){
//            movieRepository.clearMovieTable()
//            Log.d("MYTAG","movie table deleted")
//            needClearMovieTable = false
//        }

        val jsonMoviesListDeferred = if (internetStatusChecker.isInternetOn()) {
            async(Dispatchers.IO) {
                val moviesFromNetwork = movieRepository.loadMovies(pageNumber, movieType)
                Log.d("MYTAG", "movies list downloaded from network/api with PAGE number = $pageNumber")
                Log.d("MYTAG", "movies list downloaded from network/api = $moviesFromNetwork")
                movieRepository.saveMoviesToDb(moviesFromNetwork)
                val moviesInDB = movieRepository.getMoviesFromDb(pageNumber, movieType)
                Log.d("MYTAG", "movies list downloaded from network/api was saved to DB = $moviesInDB")
                movieRepository.getMoviesFromDb(pageNumber, movieType)

            }
        } else {
            async(Dispatchers.IO) { movieRepository.getMoviesFromDb(pageNumber, movieType) }
        }.await()
//        awaitAll(jsonMoviesListDeferred)
        val resList = jsonMoviesListDeferred
        Log.d("MYTAG", "movies list returned from network/db to UI = $resList")

        movieRepository.mapMovieListToUiMovieList(resList)

//        return res
    }

}

