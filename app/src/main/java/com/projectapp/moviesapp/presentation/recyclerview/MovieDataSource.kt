package com.projectapp.moviesapp.presentation.recyclerview

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.projectapp.moviesapp.data.model.Movie
import com.projectapp.moviesapp.domain.usecases.movielist.LoadMoviesUseCase
import retrofit2.HttpException

class MovieDataSource(val loadMoviesUseCase: LoadMoviesUseCase) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
//        return state.anchorPosition?.let {
//            state.closestPageToPosition(it)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
//        }
//        TODO read about and test and remake
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentLoadingPage = params.key ?: 1
            val response = loadMoviesUseCase(currentLoadingPage)
            Log.d("MYTAG", "page number# $currentLoadingPage loaded")

            val prevKey = if (currentLoadingPage == 1) null else currentLoadingPage - 1
            val nextKey = currentLoadingPage + 1
            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}