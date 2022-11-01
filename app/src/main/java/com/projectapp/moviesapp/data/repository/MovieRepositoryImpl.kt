package com.projectapp.moviesapp.data.repository

import com.projectapp.moviesapp.data.datasource.local.LocalDataSource
import com.projectapp.moviesapp.data.datasource.local.models.MovieEntity
import com.projectapp.moviesapp.data.datasource.local.models.mapToUiMovie
import com.projectapp.moviesapp.data.datasource.remotedata.RemoteDataSource
import com.projectapp.moviesapp.data.datasource.sharedprefs.SharedPrefsDataSource
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.data.model.UiMovie
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
    ): List<JsonMovie> {
        return remoteDataSource.loadPopularMovies(pageNumber, movieType)
    }

    override suspend fun getAllGenres(): List<Genre> {
        return remoteDataSource.loadAllGenres()
    }

    override suspend fun saveGenresToDb(genres: List<Genre>) {
        localDataSource.saveGenresToDb(genres)
    }

    override suspend fun saveMoviesToDb(
        list: List<MovieEntity>,
        movieType: MovieType,
    ) = withContext(Dispatchers.IO) {
        localDataSource.saveMoviesToDb(list, movieType)
    }

    override suspend fun clearMovieTable() {
        localDataSource.clearMovieTable()
    }

    override suspend fun getMoviesFromDb(
        pageNumber: Int,
        movieType: MovieType,
    ): List<MovieEntity> {
        return localDataSource.getMoviesFromDb(pageNumber, movieType)
    }

    override suspend fun mapMovieListToUiMovieList(list: List<MovieEntity>): List<UiMovie> {
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
        private var INSTANCE: MovieRepository? = null

        fun initialise(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            sharedPrefsDataSource: SharedPrefsDataSource,
        ): MovieRepository {
            synchronized(this){
                if (INSTANCE == null) {
                    INSTANCE =
                        MovieRepositoryImpl(remoteDataSource, localDataSource, sharedPrefsDataSource)
                }
                return INSTANCE ?: throw IllegalAccessException()
            }
        }

        fun get(): MovieRepository {
            return INSTANCE ?: throw IllegalAccessException()
        }
    }
}
