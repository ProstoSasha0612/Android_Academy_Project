package com.projectapp.moviesapp.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.projectapp.moviesapp.data.model.DataMovie
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType
import com.projectapp.moviesapp.domain.utils.Constants

@Dao
interface GenreDao {

    @Query("SELECT * FROM genres_table")
    suspend fun getAllGenres(): List<Genre>

    @Query("SELECT * FROM genres_table WHERE id = :id")
    suspend fun getGenreById(id: Long): Genre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenre(genre: Genre)

    @Query("SELECT * FROM movies_table")
    suspend fun getAllMovies(): List<DataMovie>

    @Query("SELECT * FROM movies_table " +
            "WHERE movie_type = :movieType " +
            "ORDER BY timeStamp " +
            "LIMIT ${Constants.MOVIES_PAGE_SIZE} " +
            "OFFSET :pageNumber*${Constants.MOVIES_PAGE_SIZE}")
    suspend fun getMoviesListPage(pageNumber: Int, movieType: String): List<DataMovie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveMovieToDb(movie: DataMovie)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMoviesToDb(list: List<DataMovie>)

    @Query("SELECT * FROM movies_table WHERE id = :id")
    suspend fun getMovieById(id: Long): DataMovie

    @Query("DELETE FROM movies_table")
    suspend fun clearMovieTable()

}

