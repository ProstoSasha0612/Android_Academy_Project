package com.projectapp.moviesapp.data.model

import com.projectapp.moviesapp.domain.usecases.movielist.MovieType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JsonMovie(

    @SerialName("id")
    val id: Long,

    @SerialName("adult")
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String?,

    @SerialName("genre_ids")
    val genreIDS: List<Long>,

    @SerialName("overview")
    val overview: String,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("release_date")
    val releaseDate: String? = null,

    @SerialName("title")
    val title: String,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Long,
)

fun List<JsonMovie>.mapToDataMoviesList(movieType: MovieType): List<DataMovie> {
    val res = mutableListOf<DataMovie>()
    this.forEach {
        val dataMovie = DataMovie(
            id = it.id,
            adult = it.adult,
            backdropPath = it.backdropPath,
            genreIDS = it.genreIDS,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            movieType = movieType,
            timeStamp = System.currentTimeMillis()
        )
        res.add(dataMovie)
    }
    return res
}