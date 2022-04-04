package com.projectapp.moviesapp.domain.repository

import com.projectapp.moviesapp.domain.model.Movie


interface MovieRepository {
    suspend fun loadMovies(): List<Movie>
    suspend fun loadMovie(movieId: Int): Movie?
}