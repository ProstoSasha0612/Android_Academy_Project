package com.projectapp.moviesapp.data.datasource.local

import com.projectapp.moviesapp.data.model.DataMovie
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType

interface LocalDataSource {

    suspend fun saveMoviesToDb(movies: List<DataMovie>)

    suspend fun getMoviesFromDb(pageNumber: Int, movieType: MovieType):List<DataMovie>

    suspend fun clearMovieTable()

    suspend fun saveGenresToDb(genres: List<Genre>)

    suspend fun geAllGenresFromDb(): List<Genre>

    suspend fun getGenreById(id: Long): Genre

}
