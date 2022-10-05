package com.projectapp.moviesapp.data.repository

import android.util.Log
import com.projectapp.moviesapp.data.datasource.local.LocalDataSource
import com.projectapp.moviesapp.data.datasource.remotedata.RemoteDataSource
import com.projectapp.moviesapp.data.datasource.sharedprefs.SharedPrefsDataSource
import com.projectapp.moviesapp.data.model.*
import com.projectapp.moviesapp.domain.repository.MovieRepository
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.job
import kotlinx.coroutines.withContext


class MovieRepositoryImpl private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val sharedPrefsDataSource: SharedPrefsDataSource,
) :
    MovieRepository {

    override suspend fun loadMovies(pageNumber: Int, movieType: MovieType): List<UiMovie> = withContext(Dispatchers.IO) {
//        val list = remoteDataSource.loadPopularMovies(pageNumber, movieType)
//        Log.d("MYTAG1","Current time after api: ${System.currentTimeMillis()}")
//        saveMoviesToDb(list)//TODO
//        Log.d("MYTAG1","Current time after save in db: ${System.currentTimeMillis()}")
        val resList = localDataSource.getMoviesFromDb(pageNumber, movieType)
//        Log.d("MYTAG1","Current time after get from db: ${System.currentTimeMillis()}")
        mapMovieListToUiMovieList(resList)
    }



    /*TODO*/
    override suspend fun loadMoviesFromDb(pageNumber: Int, movieType: MovieType) {
        localDataSource.getMoviesFromDb(pageNumber,MovieType.POPULAR)
    }

    override suspend fun getAllGenres(): List<Genre> = withContext(Dispatchers.IO) {
        remoteDataSource.loadAllGenres()
    }

    override suspend fun saveGenresToDb(genres: List<Genre>) = withContext(Dispatchers.IO) {
        localDataSource.saveGenresToDb(genres)
    }

    suspend fun saveMoviesToDb(uiMovieList: List<JsonMovie>) = withContext(Dispatchers.IO) {
//        val listJsonMovie = uiMovieList.map {it.mapToJsonMovie()}
        localDataSource.saveMoviesToDb(uiMovieList)
    }

    private suspend fun mapMovieListToUiMovieList(list: List<JsonMovie>): List<UiMovie> {
        val res = mutableListOf<UiMovie>()
        list.forEach { movie ->
            val genreList = mutableListOf<Genre>()
            movie.genreIDS.forEach { genreId ->
                val genre = localDataSource.getGenreById(genreId.toInt())
                genreList.add(genre)
            }
            res.add(movie.mapToUiMovie(genreList))
        }
        return res
    }

    companion object {
        private var instance: MovieRepository? = null

        fun initialize(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            sharedPrefsDataSource: SharedPrefsDataSource,
        ): MovieRepository {
            if (instance == null) {
                instance =
                    MovieRepositoryImpl(remoteDataSource, localDataSource, sharedPrefsDataSource)
            }

            return instance ?: throw IllegalAccessException()
        }

        fun get(): MovieRepository {
            return instance ?: throw IllegalAccessException()
        }
    }
}
