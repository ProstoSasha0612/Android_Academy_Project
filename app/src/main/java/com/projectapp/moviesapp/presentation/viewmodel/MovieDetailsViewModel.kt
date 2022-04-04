package com.projectapp.moviesapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieDetailsViewModel():ViewModel() {



    class MovieDetailsViewModelFactory:ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            TODO("Not yet implemented")
        }
    }
}