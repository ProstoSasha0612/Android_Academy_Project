package com.projectapp.moviesapp.presentation.recyclerview

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projectapp.moviesapp.R
import com.projectapp.moviesapp.data.model.UiMovie
import com.projectapp.moviesapp.databinding.ViewHolderMovieBinding
import com.projectapp.moviesapp.domain.usecases.Extra

class MoviesAdapter(
    val resources: Resources,
    val onClickListener: OnClickListener
) :
    PagingDataAdapter<UiMovie, MoviesAdapter.MovieViewHolder>(uiMovieDiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderMovieBinding.inflate(layoutInflater)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Log.d("MYTAG", "onBindViewHolder position# $position")
        Log.d("MYTAG", "onBindViewHolder item# ${getItem(position)}")
        getItem(position)?.let { holder.bind(it) }
    }

    inner class MovieViewHolder(private val binding: ViewHolderMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(uiMovie: UiMovie) {
            with(binding) {
                Glide.with(binding.root).load(uiMovie.imageUrl).error(ColorDrawable(Color.RED))
                    .into(binding.movieImage)
                filmNameTv.text = uiMovie.title
                releaseDateTv.text = uiMovie.releaseDate
                binding.reviewsCountTv.text =
                    "${uiMovie.reviewCount} ${resources.getString(R.string.reviews_text)}"
                Log.d("MYTAGGENRE","uiMOvie genres: ${uiMovie.genres}")
                genreTv.text = Extra.getGenresText(uiMovie.genres)
                ageRateTv.text = "${uiMovie.age}+"
//                setLikeColor(movie.isLiked)
                setRatingStarsColor(uiMovie.rating)
            }
            binding.root.setOnClickListener { onClickListener.onClick(uiMovie) }
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

        private fun setRatingStarsColor(rating: Double) {
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
        fun onClick(uiMovie: UiMovie)
    }

    companion object {
        val uiMovieDiffUtilCallback = object : DiffUtil.ItemCallback<UiMovie>() {

            private val payload = Any()

            override fun areItemsTheSame(oldItem: UiMovie, newItem: UiMovie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UiMovie, newItem: UiMovie): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: UiMovie, newItem: UiMovie): Any = payload
        }

        const val MOVIE_VIEW_TYPE = 0
        const val FOOTER_VIEW_TYPE = 1
    }
}