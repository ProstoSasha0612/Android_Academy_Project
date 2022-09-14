package com.projectapp.moviesapp.data.repository

import com.projectapp.moviesapp.data.datasource.local.LocalDataSource
import com.projectapp.moviesapp.data.datasource.remotedata.RemoteDataSource
import com.projectapp.moviesapp.data.datasource.sharedprefs.SharedPrefsDataSource
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.Movie
import com.projectapp.moviesapp.data.model.mapToMovie
import com.projectapp.moviesapp.domain.repository.MovieRepository
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MovieRepositoryImpl private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val sharedPrefsDataSource: SharedPrefsDataSource
) :
    MovieRepository {

    override suspend fun loadMovies(pageNumber: Int,movieType: MovieType): List<Movie> {
        val list = remoteDataSource.loadPopularMovies(pageNumber,movieType)
        val res = mutableListOf<Movie>()

        list.forEach {
            res.add(it.mapToMovie())
        }
        return res
    }


    override suspend fun getAllGenres(): List<Genre> = withContext(Dispatchers.IO) {
        remoteDataSource.loadAllGenres()
    }

    override suspend fun saveGenresToDb(genres: List<Genre>) {
        localDataSource.saveGenresToDb(genres)
    }

    companion object {
        private var instance: MovieRepository? = null

        fun initialize(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            sharedPrefsDataSource: SharedPrefsDataSource
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
