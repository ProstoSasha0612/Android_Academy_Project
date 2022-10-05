package com.projectapp.moviesapp.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.JsonMovie

@Dao
interface GenreDao {

    @Query("SELECT * FROM genres_table")
    suspend fun getAllGenres(): List<Genre>

    @Query("SELECT * FROM genres_table WHERE id = :id")
    suspend fun getGenreById(id: Int): Genre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenre(genre: Genre)


//    @Insert
//    suspend fun saveMoviesPageToDb(moviesPage: JsonMoviesPage)

    @Query("SELECT * FROM movies_table")
    suspend fun getAllMovies(): List<JsonMovie>

    @Query("SELECT * FROM movies_table LIMIT 20 OFFSET :pageNumber*20")
    suspend fun getMoviesListPage(pageNumber: Int): List<JsonMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovieToDb(movie: JsonMovie)

    @Query("SELECT * FROM movies_table WHERE id = :id")
    suspend fun getMovieById(id: Long): JsonMovie

}

