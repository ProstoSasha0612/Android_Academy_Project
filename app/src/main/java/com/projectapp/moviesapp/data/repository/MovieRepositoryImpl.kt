package com.projectapp.moviesapp.data.repository

import com.projectapp.moviesapp.data.datasource.local.LocalDataSource
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.domain.repository.MovieRepository
import com.projectapp.moviesapp.data.datasource.remotedata.RemoteDataSource
import com.projectapp.moviesapp.data.datasource.sharedprefs.SharedPrefsDataSource

internal class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val sharedPrefsDataSource: SharedPrefsDataSource
) :
    MovieRepository {

    override suspend fun loadPopularMovies(pageNumber: Int): List<JsonMovie> =
        remoteDataSource.loadPopularMovies(pageNumber)

    override suspend fun loadGenres() = remoteDataSource.loadGenres()
}
