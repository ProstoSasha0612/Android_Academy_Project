package com.projectapp.moviesapp.data.datasource.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.projectapp.moviesapp.data.datasource.local.MovieTypeConverter
import com.projectapp.moviesapp.data.model.DataMovie
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.UiMovie

@Entity(tableName = "movies_table")
@TypeConverters(MovieTypeConverter::class)
data class MovieEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "adult")
    val adult: Boolean,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,

    @ColumnInfo(name = "genre_ids")
    val genreIDS: List<Long>,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "vote_count")
    val voteCount: Long,

    @ColumnInfo(name = "timeStamp")
    val timeStamp: Long = System.currentTimeMillis()
)

fun MovieEntity.mapToUiMovie(genres: List<Genre>, width: Int = 500): UiMovie {
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
        id = this.id,
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