package com.projectapp.moviesapp.data.repository

import com.projectapp.moviesapp.data.datasource.local.LocalDataSource
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.domain.repository.MovieRepository
import com.projectapp.moviesapp.data.datasource.remotedata.RemoteDataSource
import com.projectapp.moviesapp.data.datasource.sharedprefs.SharedPrefsDataSource

class MovieRepositoryImpl private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val sharedPrefsDataSource: SharedPrefsDataSource
) :
    MovieRepository {

    override suspend fun loadPopularMovies(pageNumber: Int): List<JsonMovie> =
        remoteDataSource.loadPopularMovies(pageNumber)

    override suspend fun loadGenres() = remoteDataSource.loadGenres()

    companion object {
        private var instance: MovieRepository? = null

        fun initialize(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            sharedPrefsDataSource: SharedPrefsDataSource
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
