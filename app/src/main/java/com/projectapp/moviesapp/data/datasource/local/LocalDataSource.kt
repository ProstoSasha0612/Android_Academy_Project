package com.projectapp.moviesapp.data.datasource.local

import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.data.model.UiMovie
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType

interface LocalDataSource {

    suspend fun saveMoviesToDb(uiMovies: List<JsonMovie>)

    suspend fun saveGenresToDb(genres: List<Genre>)

    suspend fun geAllGenresFromDb(): List<Genre>

    suspend fun getGenreById(id: Int): Genre

    suspend fun getMoviesFromDb(pageNumber: Int, movieType: MovieType):List<JsonMovie>
}

