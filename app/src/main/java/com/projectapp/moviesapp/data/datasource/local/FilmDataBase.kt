package com.projectapp.moviesapp.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.projectapp.moviesapp.data.model.Genre

@Database(entities = [Genre::class], version = 1)
abstract class FilmDataBase : RoomDatabase() {
    abstract val genresDao: GenreDao

    companion object {
        fun create(context: Context) = Room.databaseBuilder(
            context,
            FilmDataBase::class.java,
            DATABASE_NAME
        )

        private const val DATABASE_NAME = "film-database"
    }
}
