package com.projectapp.moviesapp.data.datasource.local

import android.content.Context
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.UiMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(context: Context) : LocalDataSource {

    private val movieDataBase = MovieDataBase.create(context)
    private val genreDao = movieDataBase.genreDao
//    private val movieDao = movieDataBase.movieDao

    override suspend fun saveMoviesToDb(uiMovies: List<UiMovie>) {
        TODO("Not yet implemented")
    }

    override suspend fun saveGenresToDb(genres: List<Genre>) = withContext(Dispatchers.IO) {
        genres.forEach {
            genreDao.addGenre(it)
        }
    }

    override suspend fun geAllGenresFromDb(): List<Genre> = withContext(Dispatchers.IO) {
        genreDao.getAllGenres()
    }

    override suspend fun getGenreById(id: Int): Genre = withContext(Dispatchers.IO) {
        genreDao.getGenreById(id)
    }
}
