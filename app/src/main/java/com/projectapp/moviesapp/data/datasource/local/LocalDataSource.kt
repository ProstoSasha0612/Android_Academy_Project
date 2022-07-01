package com.projectapp.moviesapp.data.datasource.local

import com.projectapp.moviesapp.domain.model.Genre

interface LocalDataSource {

    fun saveGenresToDb(genres:List<Genre>)

    fun getGenresFromDb():List<Genre>
}
