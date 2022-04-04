package com.projectapp.moviesapp.data.repository

import com.projectapp.moviesapp.domain.model.Movie
import com.projectapp.moviesapp.domain.repository.MovieRepository

internal class MovieRepositoryImpl(private val jsonMovieRepository: JsonMovieRepository) :
    MovieRepository {

    override suspend fun loadMovies(): List<Movie> {
        return jsonMovieRepository.loadMovies()
    }

    override suspend fun loadMovie(movieId: Int): Movie? {
        return jsonMovieRepository.loadMovie(movieId = movieId)
    }
}