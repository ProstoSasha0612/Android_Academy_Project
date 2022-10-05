package com.projectapp.moviesapp.domain.usecases.movielist

import com.projectapp.moviesapp.domain.logic.InternetStatusChecker
import com.projectapp.moviesapp.domain.repository.MovieRepository

class LoadAndSaveGenresToDbUseCase(
    private val movieRepository: MovieRepository,
    private val internetStatusChecker: InternetStatusChecker,
) {

    suspend operator fun invoke() {
        if (internetStatusChecker.isInternetOn()) {
            val genreList = movieRepository.getAllGenres()
            movieRepository.saveGenresToDb(genres = genreList)
        }
    }
}
