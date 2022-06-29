package com.projectapp.moviesapp.data.datasource.remotedata

import com.projectapp.moviesapp.data.model.JsonMovie

interface RemoteDataSource {
    //    suspend fun loadMovies(): List<Movie>
    suspend fun loadPopularMovies(pageNumber: Int): List<JsonMovie>

    suspend fun loadGenres()

//    suspend fun loadMovie(movieId: Int): Movie?
}