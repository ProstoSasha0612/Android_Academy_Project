package com.projectapp.moviesapp.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.projectapp.moviesapp.data.datasource.local.models.MovieEntity
import com.projectapp.moviesapp.data.datasource.local.models.MovieToTypeEntity
import com.projectapp.moviesapp.data.datasource.local.models.MovieTypeEntity
import com.projectapp.moviesapp.data.model.Genre

@Database(entities = [Genre::class, MovieEntity::class, MovieTypeEntity::class, MovieToTypeEntity::class],
    version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract val movieDao: MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDataBase? = null

        fun getInstance(context: Context): MovieDataBase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context,
                    MovieDataBase::class.java,
                    DATABASE_NAME
                ).build().also {
                    INSTANCE = it
                }
            }
        }

        private const val DATABASE_NAME = "movie-database"
    }
}
