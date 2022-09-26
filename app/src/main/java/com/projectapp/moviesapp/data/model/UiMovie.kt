package com.projectapp.moviesapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.RawValue
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiMovie(
    val id: Long,

    val title: String,

    val overview: String,

    val imageUrl: String?,

    val detailImageUrl: String?,

    val rating: Double,

    val reviewCount: Int,

    val pgAge: Int,

    val releaseDate: String?,

    val genres: List<Genre>,
) : Parcelable