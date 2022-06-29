package com.projectapp.moviesapp.data.repository

import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.domain.repository.MovieRepository
import com.projectapp.moviesapp.data.datasource.remotedata.RemoteDataSource

internal class MovieRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    MovieRepository {

    override suspend fun loadPopularMovies(pageNumber: Int): List<JsonMovie> = remoteDataSource.loadPopularMovies(pageNumber)

    override suspend fun loadGenres() = remoteDataSource.loadGenres()
}
