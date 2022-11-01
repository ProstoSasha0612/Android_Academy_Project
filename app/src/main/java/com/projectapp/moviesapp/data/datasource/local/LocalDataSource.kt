package com.projectapp.moviesapp.data.datasource.local

import com.projectapp.moviesapp.data.datasource.local.models.MovieEntity
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType

interface LocalDataSource {

    suspend fun saveMoviesToDb(movies: List<MovieEntity>, movieType: MovieType)

    suspend fun getMoviesFromDb(pageNumber: Int, movieType: MovieType): List<MovieEntity>

    suspend fun clearMovieTable()

    suspend fun saveGenresToDb(genres: List<Genre>)

    suspend fun geAllGenresFromDb(): List<Genre>

    suspend fun getGenreById(id: Long): Genre

}
