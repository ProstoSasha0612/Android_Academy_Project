package com.projectapp.moviesapp.data.datasource.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_types_table")
data class MovieTypeEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "type_name")
    val typeName: String,
)