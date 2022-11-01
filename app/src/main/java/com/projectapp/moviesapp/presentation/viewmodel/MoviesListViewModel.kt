package com.projectapp.moviesapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.projectapp.moviesapp.domain.usecases.movielist.LoadAndSaveGenresToDbUseCase
import com.projectapp.moviesapp.domain.usecases.movielist.LoadMoviesUseCase
import com.projectapp.moviesapp.domain.utils.Constants
import com.projectapp.moviesapp.presentation.recyclerview.MovieDataSource
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val loadMoviesUseCase: LoadMoviesUseCase,
    private val loadAndSaveGenresToDbUSeCase: LoadAndSaveGenresToDbUseCase,
) : ViewModel() {

    init {
        viewModelScope.launch {
            //TODO move it from here to DOMAIN layer, we should don't know how it works under the hood, we will just request for loadFilms
            loadAndSaveGenresToDbUSeCase()
            Log.d("MYTAG", "genres was downloaded and saved to db")
        }
    }

    val movieListData = Pager(config = PagingConfig(pageSize = Constants.MOVIES_PAGE_SIZE)) {
        Log.d("MYTAG", "Pager configured")
        MovieDataSource(loadMoviesUseCase)
    }.flow.cachedIn(viewModelScope)

}
