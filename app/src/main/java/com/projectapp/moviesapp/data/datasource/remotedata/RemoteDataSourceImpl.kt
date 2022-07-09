package com.projectapp.moviesapp.data.datasource.remotedata

import android.util.Log
import com.projectapp.moviesapp.data.api.MoviesApiProvider
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.JsonMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RemoteDataSourceImpl : RemoteDataSource {

    private val moviesApi = MoviesApiProvider().moviesApi

    override suspend fun loadAllGenres(): List<Genre> = withContext(Dispatchers.IO) {
        val genreResponse = moviesApi.getAllGenres()
        genreResponse.genres
    }

    override suspend fun loadPopularMovies(pageNumber: Int): List<JsonMovie> =
        withContext(Dispatchers.IO) {
            val page = moviesApi.getPopularMoviesPage(pageNumber)
            val genres = moviesApi.getAllGenres()
            Log.d("MYTAG", "genres list: ${genres.toString()}")
            Log.d("MYTAG", "page movieslist size = :${page.moviesList.size}")
            page.moviesList
        }

}
