package com.projectapp.moviesapp.data.api

import com.projectapp.moviesapp.data.model.JsonMoviesPage
import com.projectapp.moviesapp.data.model.GenreResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/popular?api_key=$MOVIE_API_KEY&language=en-US&page=1")
    suspend fun getPopularMoviesDefaultPage(): JsonMoviesPage

    @GET("movie/{movieType}?api_key=$MOVIE_API_KEY&language=en-US")
    suspend fun getMoviesPage(
        @Path("movieType") movieType: String,
        @Query("page") pageNumber: Int
    ): JsonMoviesPage

    @GET("genre/movie/list?api_key=$MOVIE_API_KEY&language=en-US")
    suspend fun getAllGenres(): GenreResponse

//    fd8fc667330a0a7e778a4d14d91de9c8


    companion object {
        const val MOVIE_API_KEY = "fd8fc667330a0a7e778a4d14d91de9c8"
    }
}
