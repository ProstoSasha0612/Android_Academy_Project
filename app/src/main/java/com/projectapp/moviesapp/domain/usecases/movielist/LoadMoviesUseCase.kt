package com.projectapp.moviesapp.domain.usecases.movielist

import android.util.Log
import com.projectapp.moviesapp.data.model.UiMovie
import com.projectapp.moviesapp.domain.logic.InternetStatusChecker
import com.projectapp.moviesapp.domain.repository.MovieRepository

class LoadMoviesUseCase(
    private val movieRepository: MovieRepository,
    private val movieType: MovieType,
    private val internetStatusChecker: InternetStatusChecker,
) {
    suspend operator fun invoke(pageNumber: Int): List<UiMovie> {
        Log.d("MYTAG", "loadMoviesUSeCase calling")
        val res = movieRepository.loadMovies(pageNumber, movieType)
        Log.d("MYTAG", "loadMoviesUSeCase result List = $res")
        Log.d("MYTAG", "loadMoviesUSeCase result List size= ${res.size}")

        return res
    }
}

