package com.projectapp.moviesapp.domain.usecases.movielist

import android.util.Log
import com.projectapp.moviesapp.data.model.Movie
import com.projectapp.moviesapp.domain.repository.MovieRepository

class LoadMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(pageNumber: Int):List<Movie> {
        Log.d("MYTAG","loadMoviesUSeCase calling")
        val res = movieRepository.loadPopularMovies(pageNumber)
        Log.d("MYTAG","loadMoviesUSeCase result List = $res")
        Log.d("MYTAG","loadMoviesUSeCase result List size= ${res.size}")

        return res
    }
}

