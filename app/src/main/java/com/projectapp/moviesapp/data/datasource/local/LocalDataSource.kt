package com.projectapp.moviesapp.data.datasource.local

import com.projectapp.moviesapp.data.model.Genre

interface   LocalDataSource {

    suspend fun saveGenresToDb(genres: List<Genre>)

    suspend fun geAllGenresFromDb(): List<Genre>

    suspend fun getGenreById(id: Int): Genre
}

