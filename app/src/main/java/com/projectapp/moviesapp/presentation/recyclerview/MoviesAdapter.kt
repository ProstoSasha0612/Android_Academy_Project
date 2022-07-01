package com.projectapp.moviesapp.presentation.recyclerview

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projectapp.moviesapp.R
import com.projectapp.moviesapp.data.model.Movie
import com.projectapp.moviesapp.databinding.ViewHolderMovieBinding

class MoviesAdapter(
    val resources: Resources,
    val onClickListener: OnClickListener
) :
    ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(movieDiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderMovieBinding.inflate(layoutInflater)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(private val binding: ViewHolderMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(binding.root).load(movie.imageUrl).into(binding.movieImage)
                filmNameTv.text = movie.title
                runningTimeTv.text =
                    "${movie.runningTime} ${resources.getString(R.string.minutes_text)}"
                binding.reviewsCountTv.text =
                    "${movie.reviewCount} ${resources.getString(R.string.reviews_text)}"
//                genreTv.text = Extra.getGenresText(movie.genres)
                ageRateTv.text = "${movie.pgAge}+"
                setLikeColor(movie.isLiked)
                setRatingStarsColor(movie.rating)
            }
            binding.root.setOnClickListener { onClickListener.onClick(movie) }
        }

        private fun setLikeColor(isLiked: Boolean) {
            val likeColor = if (isLiked) R.color.pink_dark else R.color.grey
            ImageViewCompat.setImageTintList(
                binding.isFavoriteIv,
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        binding.isFavoriteIv.context,
                        likeColor
                    )
                )
            )
        }

        private fun setRatingStarsColor(rating: Int) {
            val ratingIcons = listOf(
                binding.star1Image,
                binding.star2Image,
                binding.star3Image,
                binding.star4Image,
                binding.star5Image
            )

            ratingIcons.forEachIndexed { index, imageView ->
                if (rating > index) {
                    ImageViewCompat.setImageTintList(
                        imageView,
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                imageView.context,
                                R.color.pink_dark
                            )
                        )
                    )
                }
            }
        }

    }

    interface OnClickListener {
        fun onClick(movie: Movie)
    }

    companion object {
        val movieDiffUtilCallback = object : DiffUtil.ItemCallback<Movie>() {

            private val paylaod = Any()

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: Movie, newItem: Movie): Any = paylaod
        }
    }
}