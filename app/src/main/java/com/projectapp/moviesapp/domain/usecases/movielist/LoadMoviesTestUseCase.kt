package com.projectapp.moviesapp.domain.usecases.movielist

import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.domain.repository.MovieRepository

class LoadMoviesTestUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute():List<JsonMovie>{
        return movieRepository.loadPopularMovies()
    }
}