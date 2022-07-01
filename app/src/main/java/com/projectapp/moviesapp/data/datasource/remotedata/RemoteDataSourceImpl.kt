package com.projectapp.moviesapp.data.datasource.remotedata

import android.util.Log
import com.projectapp.moviesapp.data.api.MoviesApiProvider
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.data.storage.GenreStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RemoteDataSourceImpl : RemoteDataSource {

    private val moviesApi = MoviesApiProvider().moviesApi

    override suspend fun loadGenres() {
        val genresResponse = moviesApi.getAllGenreIds()
        GenreStorage.genreList = genresResponse.genres
    }

    override suspend fun loadPopularMovies(pageNumber: Int): List<JsonMovie> =
        withContext(Dispatchers.IO) {
            val page = moviesApi.getPopularMoviesPage(pageNumber)
            val genres = moviesApi.getAllGenreIds()
            Log.d("MYTAG", "genres list: ${genres.toString()}")
            Log.d("MYTAG", "page movieslist size = :${page.moviesList.size}")
            page.moviesList
        }

}
