package com.projectapp.moviesapp.data.repository

import com.projectapp.moviesapp.data.api.MoviesApiImpl
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.domain.model.Movie
import com.projectapp.moviesapp.domain.repository.RemoteDataSource
import com.projectapp.moviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class MovieRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    MovieRepository {

    private val moviesApi = MoviesApiImpl.moviesApi // move it to remote data store

    override suspend fun loadPopularMovies(): List<JsonMovie> = withContext(Dispatchers.IO) {
        val page = moviesApi.getPopularMoviesPage()
        page[0].moviesList
    }

    override suspend fun loadMovies(): List<Movie> {
        return remoteDataSource.loadMovies()
    }

    override suspend fun loadMovie(movieId: Int): Movie? {
        return remoteDataSource.loadMovie(movieId = movieId)
    }
}