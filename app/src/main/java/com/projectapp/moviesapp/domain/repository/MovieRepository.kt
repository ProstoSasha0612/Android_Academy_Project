package com.projectapp.moviesapp.domain.repository

import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.data.model.UiMovie
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType


interface MovieRepository {
//    suspend fun loadMovies(): List<Movie>
//
//    suspend fun loadMovie(movieId: Int): Movie?

    suspend fun loadMovies(pageNumber: Int, movieType: MovieType): List<JsonMovie>

    suspend fun saveGenresToDb(genres: List<Genre>)


    suspend fun saveMoviesToDb(list: List<JsonMovie>)

    suspend fun getMoviesFromDb(pageNumber: Int, movieType: MovieType = MovieType.POPULAR): List<JsonMovie>

    suspend fun mapMovieListToUiMovieList(list: List<JsonMovie>): List<UiMovie>

    suspend fun clearMovieTable()


    suspend fun getAllGenres(): List<Genre>


    suspend fun loadMoviesFromDb(pageNumber: Int, movieType: MovieType)
}
