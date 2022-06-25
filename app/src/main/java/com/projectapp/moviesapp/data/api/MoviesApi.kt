package com.projectapp.moviesapp.data.api

import com.projectapp.moviesapp.data.model.JsonMoviesPage
import retrofit2.http.GET

interface MoviesApi {
    @GET("movie/popular?api_key=fd8fc667330a0a7e778a4d14d91de9c8&language=en-US&page=1")
    suspend fun getPopularMoviesPage(): List<JsonMoviesPage>

//    fd8fc667330a0a7e778a4d14d91de9c8

}