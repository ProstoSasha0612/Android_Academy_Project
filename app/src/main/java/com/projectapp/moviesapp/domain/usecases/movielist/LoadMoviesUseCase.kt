package com.projectapp.moviesapp.domain.usecases.movielist

import com.projectapp.moviesapp.domain.model.Movie
import com.projectapp.moviesapp.domain.repository.MovieRepository

class LoadMoviesUseCase(private val repository: MovieRepository) {
    suspend fun execute():List<Movie>{
        return repository.loadMovies()
    }
}