package com.projectapp.moviesapp.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreList(
    @SerialName("genres")
    val genres: List<Genre>
)
