package com.projectapp.moviesapp.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.projectapp.moviesapp.data.model.Genre

@Dao
interface GenreDao {

    @Query("SELECT * FROM genres_table")
    suspend fun getAllGenres(): List<Genre>

    @Query("SELECT * FROM genres_table WHERE id = :id")
    suspend fun getGenreById(id: Int): Genre

    @Insert(onConflict = REPLACE)
    suspend fun addGenre(genre: Genre)

}