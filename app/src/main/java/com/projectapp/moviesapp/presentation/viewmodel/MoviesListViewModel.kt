package com.projectapp.moviesapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.projectapp.moviesapp.data.model.Movie
import com.projectapp.moviesapp.domain.usecases.movielist.LoadGenresToDbUseCase
import com.projectapp.moviesapp.domain.usecases.movielist.LoadMoviesUseCase
import com.projectapp.moviesapp.presentation.recyclerview.MovieDataSource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val loadMoviesUseCase: LoadMoviesUseCase,
    private val loadGenresToDbUSeCase: LoadGenresToDbUseCase
) : ViewModel() {

    val movieListData = Pager(config = PagingConfig(pageSize = PAGE_SIZE)) {
        Log.d("MYTAG","Pager configured")
        MovieDataSource(loadMoviesUseCase)
    }.flow.cachedIn(viewModelScope)

//    init {
//        viewModelScope.launch {
//            loadGenresToDbUSeCase()
//            val res = loadMoviesUseCase(1)
//            _moviesLiveData.value = res
//        }
//    }

    companion object {
        const val PAGE_SIZE = 20
    }

}