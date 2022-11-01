package com.projectapp.moviesapp.domain.repository

import com.projectapp.moviesapp.data.datasource.local.models.MovieEntity
import com.projectapp.moviesapp.data.model.DataMovie
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.data.model.UiMovie
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType


interface MovieRepository {

    suspend fun loadMovies(pageNumber: Int, movieType: MovieType): List<JsonMovie>

    suspend fun saveMoviesToDb(list: List<MovieEntity>,movieType: MovieType)

    suspend fun getMoviesFromDb(pageNumber: Int, movieType: MovieType): List<MovieEntity>

    suspend fun mapMovieListToUiMovieList(list: List<MovieEntity>): List<UiMovie>

    suspend fun clearMovieTable()


    suspend fun getAllGenres(): List<Genre>

    suspend fun saveGenresToDb(genres: List<Genre>)
}
