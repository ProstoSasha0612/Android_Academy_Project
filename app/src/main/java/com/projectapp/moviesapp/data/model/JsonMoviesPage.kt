package com.projectapp.moviesapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JsonMoviesPage (
    val page: Long,
    @SerialName("results")
    val moviesList: List<JsonMovie>,

    @SerialName("total_pages")
    val totalPages: Long,

    @SerialName("total_results")
    val totalResults: Long
)

