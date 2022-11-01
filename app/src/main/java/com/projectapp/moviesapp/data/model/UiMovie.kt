package com.projectapp.moviesapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiMovie(
    val id: Long,

    val title: String,

    val overview: String,

    val imageUrl: String?,

    val detailImageUrl: String?,

    val rating: Double,

    val reviewCount: Long,

    val age: Int,

    val releaseDate: String?,

    val genres: List<Genre>,
) : Parcelable


//fun UiMovie.mapToJsonMovie(): JsonMovie {
//    return JsonMovie(
//        originalId = id,
//        adult = this.age > 13,
//        backdropPath = this.imageUrl,
//        genreIDS = this.genres.map { it.id.toLong() },
//        overview = this.overview,
//        posterPath = this.imageUrl,
//        releaseDate = this.releaseDate,
//        title = this.title,
//        voteAverage = this.rating,
//        voteCount = this.reviewCount,
//        primaryId = id
//    )
//}