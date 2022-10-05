package com.projectapp.moviesapp.data.model

import androidx.room.*
import com.projectapp.moviesapp.data.datasource.local.MovieTypeConverter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "movies_table")
@TypeConverters(MovieTypeConverter::class)
data class JsonMovie(
    @SerialName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @SerialName("adult")
    @ColumnInfo(name = "adult")
    val adult: Boolean,

    @SerialName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,

    @SerialName("genre_ids")
    @ColumnInfo(name = "genre_ids")
    val genreIDS: List<Long>,

//    @SerialName("original_language")
//    @ColumnInfo(name = "original_language")
//    val originalLanguage: String,

//    @SerialName("original_title")
//    @ColumnInfo(name = "original_title")
//    val originalTitle: String,

    @SerialName("overview")
    @ColumnInfo(name = "overview")
    val overview: String,

//    @SerialName("popularity")
//    @ColumnInfo(name = "popularity")
//    val popularity: Double,

    @SerialName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @SerialName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null,

    @SerialName("title")
    @ColumnInfo(name = "title")
    val title: String,

//    @SerialName("video")
//    @ColumnInfo(name = "video")
//    val video: Boolean,

    @SerialName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    @ColumnInfo(name = "vote_count")
    val voteCount: Long,
)

fun JsonMovie.mapToUiMovie(genres: List<Genre>, width: Int = 500): UiMovie {
//    val title = originalTitle
    val overview = overview

    val startOfImageUrl = "https://image.tmdb.org/t/p/w$width/"
    val imageUrl = if (posterPath.isNullOrEmpty()) null else startOfImageUrl + posterPath
    val detailImageUrl =
        if (backdropPath.isNullOrEmpty()) imageUrl else startOfImageUrl + backdropPath

    val rating = voteAverage / 2
    val reviewCount = voteCount
    val pgAge = if (adult) 16 else 13
    val releaseDate = releaseDate

    return UiMovie(
        id = id,
        title = title,
        overview = overview,
        imageUrl = imageUrl,
        detailImageUrl = detailImageUrl,
        rating = rating,
        reviewCount = reviewCount,
        age = pgAge,
        releaseDate = releaseDate,
        genres = genres
    )
}