package com.projectapp.moviesapp.domain.repository

import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.data.model.Movie


interface MovieRepository {
//    suspend fun loadMovies(): List<Movie>
//
//    suspend fun loadMovie(movieId: Int): Movie?

    suspend fun loadPopularMovies(pageNumber: Int): List<Movie>

    suspend fun saveGenresToDb(genres: List<Genre>)



    suspend fun getAllGenres(): List<Genre>

}
