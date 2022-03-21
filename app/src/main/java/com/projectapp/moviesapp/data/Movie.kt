package com.projectapp.moviesapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val filmName: String,
    val imageUrl: String,
    val genres: String,
    val rating: Double,
    val duration: Int,
    val reviewsCount: Int,
    val ageRate: Int,
    val isFavorite: Boolean,
    val storyLine: String,
    val actorsList: List<Actor>
) : Parcelable