package com.projectapp.moviesapp.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.JsonMovie

@Database(entities = [Genre::class,/* JsonMovie::class*/], version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract val genreDao: GenreDao
//    abstract val movieDao: MovieDao

    companion object {
        fun create(context: Context) = Room.databaseBuilder(
            context,
            MovieDataBase::class.java,
            DATABASE_NAME
        ).build()

        private const val DATABASE_NAME = "movie-database"
    }
}
