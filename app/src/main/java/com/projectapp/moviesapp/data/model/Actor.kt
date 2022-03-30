package com.projectapp.moviesapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Actor(val id: Int, val name: String, val imageUrl: String) : Parcelable