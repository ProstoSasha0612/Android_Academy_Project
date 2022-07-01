package com.projectapp.moviesapp.data.datasource.local

import android.content.Context
import com.projectapp.moviesapp.data.model.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(context: Context) : LocalDataSource {

    private val dao = FilmDataBase.create(context).build().genresDao

    override suspend fun saveGenresToDb(genres: List<Genre>) = withContext(Dispatchers.IO) {
        genres.forEach {
            dao.addGenre(it)
        }
    }

    override suspend fun geAllGenresFromDb(): List<Genre> = withContext(Dispatchers.IO) {
        dao.getAllGenres()
    }

    override suspend fun getGenreById(id: Int): Genre = withContext(Dispatchers.IO) {
        dao.getGenreById(id)
    }
}
