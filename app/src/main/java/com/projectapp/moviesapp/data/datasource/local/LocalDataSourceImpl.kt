package com.projectapp.moviesapp.data.datasource.local

import android.content.Context
import com.projectapp.moviesapp.domain.model.Genre

class LocalDataSourceImpl(private val context: Context) : LocalDataSource {

    private val db = FilmDataBase.create(context)
    override fun saveGenresToDb(genres: List<Genre>) {
        TODO("Not yet implemented")
    }

    override fun getGenresFromDb(): List<Genre> {
        TODO("Not yet implemented")
    }
}
