package com.projectapp.moviesapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.projectapp.moviesapp.data.model.UiMovie

class MovieDetailsViewModel(
    val uiMovie: UiMovie,
) : ViewModel() {

//    fun getGenresText() = Extra.getGenresText(movie?.genres)

}
