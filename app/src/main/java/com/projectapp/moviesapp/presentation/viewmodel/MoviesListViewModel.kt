package com.projectapp.moviesapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.projectapp.moviesapp.domain.usecases.movielist.LoadAndSaveGenresToDbUseCase
import com.projectapp.moviesapp.domain.usecases.movielist.LoadMoviesUseCase
import com.projectapp.moviesapp.presentation.recyclerview.MovieDataSource
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val loadMoviesUseCase: LoadMoviesUseCase,
    private val loadAndSaveGenresToDbUSeCase: LoadAndSaveGenresToDbUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            loadAndSaveGenresToDbUSeCase()
        }
    }

    val movieListData = Pager(config = PagingConfig(pageSize = PAGE_SIZE)) {
        Log.d("MYTAG","Pager configured")
        MovieDataSource(loadMoviesUseCase)
    }.flow.cachedIn(viewModelScope)


    companion object {
        const val PAGE_SIZE = 20
    }

}
