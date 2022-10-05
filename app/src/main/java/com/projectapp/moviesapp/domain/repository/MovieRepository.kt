package com.projectapp.moviesapp.domain.repository

import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.UiMovie
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType


interface MovieRepository {
//    suspend fun loadMovies(): List<Movie>
//
//    suspend fun loadMovie(movieId: Int): Movie?

    suspend fun loadMovies(pageNumber: Int,movieType: MovieType): List<UiMovie>

    suspend fun saveGenresToDb(genres: List<Genre>)



    suspend fun getAllGenres(): List<Genre>


    suspend fun loadMoviesFromDb(pageNumber: Int, movieType: MovieType)
}
