package com.projectapp.moviesapp.data.datasource.local

import androidx.room.TypeConverter
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType

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

    @TypeConverter
    fun fromMovieType(movieType: MovieType): String {
        return movieType.typeName.lowercase()
    }

    @TypeConverter
    fun toMovieType(movieType: String): MovieType {
        return MovieType.valueOf(movieType)
    }

}