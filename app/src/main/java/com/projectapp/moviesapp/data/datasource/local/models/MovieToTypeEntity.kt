package com.projectapp.moviesapp.data.datasource.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "movie_to_type_table", primaryKeys = ["id","type_name"])
data class MovieToTypeEntity(
    @ColumnInfo(name = "id")
    val movieId:Long,

    @ColumnInfo(name = "type_name")
    val typeName:String
)