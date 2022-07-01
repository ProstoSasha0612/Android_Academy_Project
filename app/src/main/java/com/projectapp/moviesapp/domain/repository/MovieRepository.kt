package com.projectapp.moviesapp.domain.repository

import com.projectapp.moviesapp.data.model.JsonMovie


interface MovieRepository {
//    suspend fun loadMovies(): List<Movie>
//
//    suspend fun loadMovie(movieId: Int): Movie?

    suspend fun loadPopularMovies(pageNumber:Int):List<JsonMovie>

    suspend fun loadGenres()
}
