package com.projectapp.moviesapp.data.datasource.local

import androidx.room.*
import com.projectapp.moviesapp.data.datasource.local.models.MovieEntity
import com.projectapp.moviesapp.data.datasource.local.models.MovieToTypeEntity
import com.projectapp.moviesapp.data.datasource.local.models.relations.TypeWithMovies
import com.projectapp.moviesapp.data.model.Genre

@Dao
interface MovieDao {

    @Query("SELECT * FROM genres_table")
    suspend fun getAllGenres(): List<Genre>

    @Query("SELECT * FROM genres_table WHERE id = :id")
    suspend fun getGenreById(id: Long): Genre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenre(genre: Genre)

    @Transaction
    @Query("SELECT * FROM movie_to_type_table WHERE type_name =:movieType LIMIT 20 OFFSET 0*:pageNumber ")
    suspend fun getTypeWithMovies(movieType: String, pageNumber: Int): List<TypeWithMovies>

    //old Query
    @Transaction
    @Query("SELECT * FROM movies_table WHERE id IN (SELECT id FROM movie_to_type_table WHERE type_name = :movieType LIMIT 20 OFFSET :pageNumber*20 ) ORDER BY timeStamp")
    suspend fun getMoviesListPage(pageNumber: Int, movieType: String): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieToType(movieToTypeEntity: MovieToTypeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieToDb(movie: MovieEntity)

    //save movies in Movie table and in MovieToType table
    @Transaction
    suspend fun insertMovieToDb(movie: MovieEntity, movieType: String) {
        val movieToTypeEntity = MovieToTypeEntity(movieId = movie.id, typeName = movieType)
        insertMovieToType(movieToTypeEntity)
        insertMovieToDb(movie)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMoviesToDb(list: List<MovieEntity>)

    @Query("SELECT * FROM movies_table WHERE id = :id")
    suspend fun getMovieById(id: Long): MovieEntity

    @Query("DELETE FROM movies_table")
    suspend fun clearMovieTable()

}

