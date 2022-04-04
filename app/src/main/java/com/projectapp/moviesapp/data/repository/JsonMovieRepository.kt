package com.projectapp.moviesapp.data.repository

import com.projectapp.moviesapp.domain.model.Movie

interface JsonMovieRepository {
    suspend fun loadMovies(): List<Movie>
    suspend fun loadMovie(movieId: Int): Movie?
}