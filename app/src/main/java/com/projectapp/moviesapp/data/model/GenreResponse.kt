package com.projectapp.moviesapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    @SerialName("genres")
    val genres: List<Genre>
)
