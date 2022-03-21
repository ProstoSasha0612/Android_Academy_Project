package com.projectapp.moviesapp.data

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projectapp.moviesapp.R
import com.projectapp.moviesapp.databinding.ViewHolderMovieBinding

class MoviesAdapter(
    private val list: List<Movie>,
    val resources: Resources,
    val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderMovieBinding.inflate(layoutInflater)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MovieViewHolder(private val binding: ViewHolderMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie) {
            with(binding) {
//                Glide.with(binding.root).load(movie.imageUrl).into(binding.posterImage)
                filmNameTv.text = movie.filmName
                durationTv.text = "${movie.duration} ${resources.getString(R.string.minutes_text)}"
                binding.reviewsCountTv.text =
                    "${movie.reviewsCount} ${resources.getString(R.string.reviews_text)}"
                genreTv.text = movie.genres
                ageRateTv.text = "${movie.ageRate}+"
                if (movie.isFavorite) {
                    isFavoriteIv.setBackgroundResource(R.drawable.ic_like_pink)
                } else {
                    isFavoriteIv.setBackgroundResource(R.drawable.ic_like_white)
                }
            }
            binding.root.setOnClickListener { onClickListener.onClick(movie) }
        }
    }

    interface OnClickListener {
        fun onClick(movie: Movie)
    }
}