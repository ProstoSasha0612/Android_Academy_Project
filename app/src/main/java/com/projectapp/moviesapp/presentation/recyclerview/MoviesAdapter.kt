package com.projectapp.moviesapp.presentation.recyclerview

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projectapp.moviesapp.R
import com.projectapp.moviesapp.domain.model.Movie
import com.projectapp.moviesapp.databinding.ViewHolderMovieBinding
import com.projectapp.moviesapp.presentation.ui.FragmentMovieDetails

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
                Glide.with(binding.root).load(movie.imageUrl).into(binding.movieImage)
                filmNameTv.text = movie.title
                //TODO Rating function
                runningTimeTv.text =
                    "${movie.runningTime} ${resources.getString(R.string.minutes_text)}"
                binding.reviewsCountTv.text =
                    "${movie.reviewCount} ${resources.getString(R.string.reviews_text)}"
                genreTv.text = FragmentMovieDetails.getGenresTextViewText(movie.genres)
                ageRateTv.text = "${movie.pgAge}+"
                isFavoriteIv.setBackgroundResource(getMovieIsLikedDrawableId(movie.isLiked))
                if (movie.isLiked) {
                    isFavoriteIv.setBackgroundResource(R.drawable.ic_like_pink)
                } else {
                    isFavoriteIv.setBackgroundResource(R.drawable.ic_like_white)
                }
            }
            binding.root.setOnClickListener { onClickListener.onClick(movie) }
        }

        private fun getMovieIsLikedDrawableId(isLiked: Boolean): Int {
            return if (isLiked) R.drawable.ic_like_pink else R.drawable.ic_like_white
        }
    }

    interface OnClickListener {
        fun onClick(movie: Movie)
    }
}