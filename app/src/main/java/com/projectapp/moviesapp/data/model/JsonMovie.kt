package com.projectapp.moviesapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JsonMovie(
    @SerialName("id")
    val id: Long,

    @SerialName("adult")
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String,

    @SerialName("genre_ids")
    val genreIDS: List<Long>,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    @SerialName("overview")
    val overview: String,

    @SerialName("popularity")
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("release_date")
    val releaseDate: String,

    @SerialName("title")
    val title: String,

    @SerialName("video")
    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Long
)

fun JsonMovie.mapToMovie(width: Int = 500): Movie {
    val title = originalTitle
    val overview = overview

    val startOfImageUrl = "https://image.tmdb.org/t/p/w$width/"
    val imageUrl = startOfImageUrl + posterPath
    val detailImageUrl = startOfImageUrl + backdropPath

    val rating = voteAverage / 2
    val reviewCount = voteCount
    val pgAge = if (adult) 16 else 13
    val releaseDate = releaseDate

    return Movie(
        id = id,
        title = title,
        overview = overview,
        imageUrl = imageUrl,
        detailImageUrl = detailImageUrl,
        rating = rating,
        reviewCount = reviewCount.toInt(),
        pgAge = pgAge,
        releaseDate = releaseDate
    )
}