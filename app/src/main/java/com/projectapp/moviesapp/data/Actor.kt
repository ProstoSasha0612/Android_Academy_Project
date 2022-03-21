package com.projectapp.moviesapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Actor(val name: String, val imageUrl: String):Parcelable {
}