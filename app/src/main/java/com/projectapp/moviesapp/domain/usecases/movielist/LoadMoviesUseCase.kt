package com.projectapp.moviesapp.domain.usecases.movielist

import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.domain.repository.MovieRepository
class LoadMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(pageNumber: Int):List<JsonMovie> {
        return movieRepository.loadPopularMovies(pageNumber)
    }
}

