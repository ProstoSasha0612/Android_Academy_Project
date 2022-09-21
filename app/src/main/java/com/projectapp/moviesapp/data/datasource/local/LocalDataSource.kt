package com.projectapp.moviesapp.data.datasource.local

import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.UiMovie

interface LocalDataSource {

    suspend fun saveMoviesToDb(uiMovies: List<UiMovie>)

    suspend fun saveGenresToDb(genres: List<Genre>)

    suspend fun geAllGenresFromDb(): List<Genre>

    suspend fun getGenreById(id: Int): Genre
}

