package com.projectapp.moviesapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.projectapp.moviesapp.data.model.Movie

class MovieDetailsViewModel(
    val movie: Movie?,
) : ViewModel() {

//    fun getGenresText() = Extra.getGenresText(movie?.genres)

}
