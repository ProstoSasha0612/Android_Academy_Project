package com.projectapp.moviesapp.data.datasource.remotedata

import android.util.Log
import com.projectapp.moviesapp.data.api.MoviesApiProvider
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RemoteDataSourceImpl : RemoteDataSource {

    private val moviesApi = MoviesApiProvider().moviesApi

    override suspend fun loadAllGenres(): List<Genre> = withContext(Dispatchers.IO) {
        val genreResponse = moviesApi.getAllGenres()
        genreResponse.genres
    }

    override suspend fun loadPopularMovies(pageNumber: Int, movieType: MovieType): List<JsonMovie> =
        withContext(Dispatchers.IO) {
            Log.d("MYTAGremoteDS", "page loading called")
            val page = moviesApi.getMoviesPage(movieType.typeName, pageNumber)
            Log.d("MYTAG remoteDS", "page loading ended")
            Log.d("MYTAG remoteDS", "page movieslist size = :${page.moviesList.size}")
            Log.d("MYTAG remoteDS", "page movieslist = :${page.moviesList.size}")
            page.moviesList
        }
}
