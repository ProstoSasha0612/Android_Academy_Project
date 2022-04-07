package com.projectapp.moviesapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projectapp.moviesapp.domain.model.Movie
import com.projectapp.moviesapp.domain.usecases.moviedetails.GetGenresTextUseCase

class MovieDetailsViewModel(
    val movie: Movie?,
    private val getGenresTextUseCase: GetGenresTextUseCase
) : ViewModel() {

    fun getGenresText() = getGenresTextUseCase.execute(movie?.genres)

}