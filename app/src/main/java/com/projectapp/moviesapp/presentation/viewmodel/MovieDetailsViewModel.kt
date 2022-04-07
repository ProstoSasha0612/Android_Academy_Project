package com.projectapp.moviesapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.projectapp.moviesapp.domain.model.Movie
import com.projectapp.moviesapp.domain.usecases.Extra

class MovieDetailsViewModel(
    val movie: Movie?,
) : ViewModel() {

    fun getGenresText() = Extra.getGenresText(movie?.genres)

}