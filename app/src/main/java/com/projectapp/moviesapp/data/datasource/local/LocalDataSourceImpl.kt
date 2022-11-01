package com.projectapp.moviesapp.data.datasource.local

import android.content.Context
import com.projectapp.moviesapp.data.datasource.local.models.MovieEntity
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(context: Context) : LocalDataSource {

    private val movieDataBase = MovieDataBase.getInstance(context)
    private val movieDao = movieDataBase.movieDao

    //save movies in Movie table and in MovieToType table
    override suspend fun saveMoviesToDb(movies: List<MovieEntity>, movieType: MovieType) =
        withContext(Dispatchers.IO) {
            val movieTypeStr = movieType.typeName.lowercase()
            movies.forEach { movie ->
                movieDao.insertMovieToDb(movie,movieTypeStr)
            }
        }

    override suspend fun saveGenresToDb(genres: List<Genre>) = withContext(Dispatchers.IO) {
        genres.forEach {
            movieDao.addGenre(it)
        }
    }

    override suspend fun geAllGenresFromDb(): List<Genre> = withContext(Dispatchers.IO) {
        movieDao.getAllGenres()
    }

    override suspend fun getGenreById(id: Long): Genre = withContext(Dispatchers.IO) {
        movieDao.getGenreById(id)
    }

    override suspend fun getMoviesFromDb(pageNumber: Int, movieType: MovieType): List<MovieEntity> =
        withContext(Dispatchers.IO) {
            // - 1 because in db pages starts from 0, in api from 1
            val movieTypeStr = movieType.typeName.lowercase()
            val res = movieDao.getTypeWithMovies(movieTypeStr,pageNumber - CONST_EQ_1)
            res[0].moviesList
        }

    override suspend fun clearMovieTable() {
        movieDao.clearMovieTable()
    }

    companion object {
        private const val CONST_EQ_1 = 1
    }
}
