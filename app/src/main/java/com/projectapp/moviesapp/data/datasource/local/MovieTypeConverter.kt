package com.projectapp.moviesapp.data.datasource.local

import androidx.room.TypeConverter

class MovieTypeConverter {

    @TypeConverter
    fun fromGenreIds(genreIds: List<Long>): String {
        return genreIds.joinToString(separator = ",")
    }

    @TypeConverter
    fun toGenreIds(data: String): List<Long> {
        val listStrings = data.split(",")
        return listStrings.map { it.toLong() }
    }
}