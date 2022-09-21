package com.projectapp.moviesapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "genres_table")
@Parcelize
data class Genre(
    @SerialName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @SerialName("name")
    @ColumnInfo(name = "name")
    val name: String
):Parcelable