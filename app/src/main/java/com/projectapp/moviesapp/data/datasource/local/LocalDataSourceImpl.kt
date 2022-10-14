package com.projectapp.moviesapp.data.datasource.local

import android.content.Context
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(context: Context) : LocalDataSource {

    private val movieDataBase = MovieDataBase.create(context)
    private val genreDao = movieDataBase.genreDao
//    private val movieDao = movieDataBase.movieDao

    override suspend fun saveMoviesToDb(movies: List<JsonMovie>) = withContext(Dispatchers.IO) {
        movies.forEach { genreDao.saveMovieToDb(it) }
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

    override suspend fun getMoviesFromDb(pageNumber: Int, movieType: MovieType): List<JsonMovie> =
        withContext(Dispatchers.IO) {
            // - 1 because in db pages starts from 0, in api from 1
            genreDao.getMoviesListPage(pageNumber - CONST_EQ_1)
        }

    override suspend fun clearMovieTable() {
        genreDao.clearMovieTable()
    }

    companion object{
        private const val CONST_EQ_1 = 1
    }
}
