package com.projectapp.moviesapp.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.projectapp.moviesapp.data.model.DataMovie
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.data.model.JsonMoviesPage

@Dao
interface MovieDao {

    @Insert
    suspend fun saveMoviesPageToDb(moviesPage: JsonMoviesPage)



    @Query("SELECT * FROM movies_table")
    suspend fun getAllMovies(): List<DataMovie>

    @Insert
    suspend fun addMovieToDb(movie: DataMovie)

    @Query("SELECT * FROM movies_table WHERE id = :id")
    suspend fun getMovieById(id: Int)

//    @Query("SELECT 20 * FROM movies_table ")
}