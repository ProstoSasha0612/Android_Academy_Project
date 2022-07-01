package com.projectapp.moviesapp.data.datasource.local

import androidx.room.Dao
import androidx.room.Query
import com.projectapp.moviesapp.domain.model.Genre

@Dao
interface GenreDao {

    @Query("SELECT * FROM genres WHERE id = :id")
    suspend fun getGenreById(id: Int):Genre

}