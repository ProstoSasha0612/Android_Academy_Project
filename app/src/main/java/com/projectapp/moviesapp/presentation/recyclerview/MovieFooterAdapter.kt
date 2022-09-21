package com.projectapp.moviesapp.presentation.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.projectapp.moviesapp.databinding.ViewHolderMovieFooterBinding

class MovieFooterAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MovieFooterAdapter.FooterViewHolder>() {
    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        Log.d("MYTAG", "footer VH created!")
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderMovieFooterBinding.inflate(layoutInflater)
        return FooterViewHolder(binding, retry)
    }

    class FooterViewHolder(private val binding: ViewHolderMovieFooterBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryBtn.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.tvErrorMessage.text = loadState.error.localizedMessage
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryBtn.isVisible = loadState is LoadState.Error
            binding.tvErrorMessage.isVisible = loadState is LoadState.Error
        }
    }

    override fun getStateViewType(loadState: LoadState): Int {
        if (loadState == LoadState.Loading || loadState is LoadState.Error) {
            return MoviesAdapter.FOOTER_VIEW_TYPE
        }
        return MoviesAdapter.MOVIE_VIEW_TYPE
    }
}