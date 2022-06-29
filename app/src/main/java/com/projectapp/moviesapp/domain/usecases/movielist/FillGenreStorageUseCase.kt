package com.projectapp.moviesapp.domain.usecases.movielist

import com.projectapp.moviesapp.domain.repository.MovieRepository

class FillGenreStorageUseCase(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(){
        movieRepository.loadGenres()
    }
}
