package com.projectapp.moviesapp.data.repository

import com.projectapp.moviesapp.data.datasource.local.LocalDataSource
import com.projectapp.moviesapp.data.datasource.remotedata.RemoteDataSource
import com.projectapp.moviesapp.data.datasource.sharedprefs.SharedPrefsDataSource
import com.projectapp.moviesapp.data.model.*
import com.projectapp.moviesapp.domain.repository.MovieRepository
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MovieRepositoryImpl private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val sharedPrefsDataSource: SharedPrefsDataSource,
) :
    MovieRepository {

    override suspend fun loadMovies(
        pageNumber: Int,
        movieType: MovieType,
    ): List<JsonMovie> = withContext(Dispatchers.IO) {
        remoteDataSource.loadPopularMovies(pageNumber, movieType)
    }

    override suspend fun getAllGenres(): List<Genre> = withContext(Dispatchers.IO) {
        remoteDataSource.loadAllGenres()
    }

    override suspend fun saveGenresToDb(genres: List<Genre>) = withContext(Dispatchers.IO) {
        localDataSource.saveGenresToDb(genres)
    }

    override suspend fun saveMoviesToDb(list: List<DataMovie>) /*= withContext(Dispatchers.IO)*/ {
//        val listJsonMovie = uiMovieList.map {it.mapToJsonMovie()}
        localDataSource.saveMoviesToDb(list)
    }

    override suspend fun clearMovieTable() {
        localDataSource.clearMovieTable()
    }

    override suspend fun getMoviesFromDb(
        pageNumber: Int,
        movieType: MovieType,
    ): List<DataMovie> = withContext(Dispatchers.IO) {
        localDataSource.getMoviesFromDb(pageNumber, movieType)
    }

    override suspend fun mapMovieListToUiMovieList(list: List<DataMovie>): List<UiMovie> {
        val res = mutableListOf<UiMovie>()
        list.forEach { movie ->
            val genreList = mutableListOf<Genre>()
            movie.genreIDS.forEach { genreId ->
                val genre = localDataSource.getGenreById(genreId)
                genreList.add(genre)
            }
            res.add(movie.mapToUiMovie(genreList))
        }
        return res
    }

    companion object {
        private var instance: MovieRepository? = null

        fun initialize(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            sharedPrefsDataSource: SharedPrefsDataSource,
        ): MovieRepository {
            if (instance == null) {
                instance =
                    MovieRepositoryImpl(remoteDataSource, localDataSource, sharedPrefsDataSource)
            }

            return instance ?: throw IllegalAccessException()
        }

        fun get(): MovieRepository {
            return instance ?: throw IllegalAccessException()
        }
    }
}
