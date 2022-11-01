package com.projectapp.moviesapp.domain.logic

import android.util.Log
import com.projectapp.moviesapp.domain.repository.MovieRepository

class TablesCleaner(
    private val movieRepository: MovieRepository,
    internetStatusChecker: InternetStatusChecker,
) {

    //if internet is on -> we need to clear movies table
    private var shouldClearTable = internetStatusChecker.isInternetOn()
    private var instance: TablesCleaner? = null

    fun get(
        movieRepository: MovieRepository,
        internetStatusChecker: InternetStatusChecker,
    ): TablesCleaner {
        if (instance == null) {
            instance = TablesCleaner(movieRepository, internetStatusChecker)
        }
        return instance as TablesCleaner
    }

    suspend fun clearMoviesTableIfNeeded() {
        if (shouldClearTable) {
            movieRepository.clearMovieTable()
            Log.d("MYTAG","Movies table cleared")
            shouldClearTable = false
        }
    }

}