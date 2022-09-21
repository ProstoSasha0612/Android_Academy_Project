package com.projectapp.moviesapp.presentation.recyclerview

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.projectapp.moviesapp.data.model.UiMovie
import com.projectapp.moviesapp.domain.usecases.movielist.LoadMoviesUseCase
import java.net.ConnectException

class MovieDataSource(val loadMoviesUseCase: LoadMoviesUseCase) : PagingSource<Int, UiMovie>() {

    override fun getRefreshKey(state: PagingState<Int, UiMovie>): Int? {
//        return state.anchorPosition?.let {
//            state.closestPageToPosition(it)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
//        }
//        TODO read about and test and remake
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UiMovie> {
        return try {
            val currentLoadingPage = params.key ?: 1
            val response = loadMoviesUseCase(currentLoadingPage)
            Log.d("MYTAG", "page number# $currentLoadingPage loaded")


            val prevKey = if (currentLoadingPage == 1) null else currentLoadingPage - 1
            val nextKey = if (response.isNotEmpty()) currentLoadingPage + 1 else null

//            if (response.isEmpty()) {
//                return LoadResult.Page(response, prevKey = prevKey, nextKey = null)
//            }

            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            if(e is ConnectException){
                Log.d("MYTAG1","MovieDataSource LoadResult reconnect called")
                load(params)
            }
            LoadResult.Error(e)
        }
    }
}