package com.projectapp.moviesapp.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.JsonMovie
import com.projectapp.moviesapp.domain.utils.Constants

@Dao
interface GenreDao {

    @Query("SELECT * FROM genres_table")
    suspend fun getAllGenres(): List<Genre>

    @Query("SELECT * FROM genres_table WHERE id = :id")
    suspend fun getGenreById(id: Int): Genre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenre(genre: Genre)

    @Query("SELECT * FROM movies_table")
    suspend fun getAllMovies(): List<JsonMovie>

    @Query("SELECT * FROM movies_table ORDER BY timeStamp ASC LIMIT ${Constants.MOVIES_PAGE_SIZE} OFFSET :pageNumber*${Constants.MOVIES_PAGE_SIZE}")
    suspend fun getMoviesListPage(pageNumber: Int): List<JsonMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovieToDb(movie: JsonMovie)

    @Query("SELECT * FROM movies_table WHERE id = :id")
    suspend fun getMovieById(id: Long): JsonMovie

    @Query("DELETE FROM movies_table")
    suspend fun clearMovieTable()

}

