package com.projectapp.moviesapp.domain.usecases.movielist

import com.projectapp.moviesapp.domain.repository.MovieRepository

class SaveGenresToDbUSeCase(private val movieRepository: MovieRepository) {

    suspend operator fun invoke() {
        val list = movieRepository.getAllGenres()
        movieRepository.saveGenresToDb(genres = list)
    }
}
