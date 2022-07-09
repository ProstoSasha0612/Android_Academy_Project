package com.projectapp.moviesapp.domain.usecases.movielist

import com.projectapp.moviesapp.data.model.Movie
import com.projectapp.moviesapp.domain.repository.MovieRepository

class LoadMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(pageNumber: Int):List<Movie> {
        return movieRepository.loadPopularMovies(pageNumber)
    }
}

