package com.projectapp.moviesapp.domain.usecases.movielist

import com.projectapp.moviesapp.domain.repository.MovieRepository

class LoadAndSaveGenresToDbUseCase(private val movieRepository: MovieRepository) {

    suspend operator fun invoke() {
        val genreList = movieRepository.getAllGenres()
        movieRepository.saveGenresToDb(genres = genreList)
    }
}
