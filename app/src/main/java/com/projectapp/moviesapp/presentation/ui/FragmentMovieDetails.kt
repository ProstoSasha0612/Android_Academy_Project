package com.projectapp.moviesapp.presentation.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.projectapp.moviesapp.R
import com.projectapp.moviesapp.data.model.Actor
import com.projectapp.moviesapp.data.model.UiMovie
import com.projectapp.moviesapp.presentation.recyclerview.ActorsAdapter
import com.projectapp.moviesapp.databinding.FragmentMovieDetailsBinding
import com.projectapp.moviesapp.domain.usecases.Extra
import com.projectapp.moviesapp.presentation.viewmodel.MovieDetailsViewModel
import com.projectapp.moviesapp.presentation.viewmodel.factory.MovieDetailsViewModelFactory

class FragmentMovieDetails : Fragment() {


    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val uiMovie by lazy { arguments?.getParcelable<UiMovie>(KEY_MOVIE)?: throw IllegalArgumentException() }
    private val vm by lazy {
        ViewModelProvider(
            this,
            MovieDetailsViewModelFactory(uiMovie)
        ).get(MovieDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillDataToViews()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun fillDataToViews() {
//        val movie = arguments?.getParcelable<Movie>(KEY_MOVIE)
        Glide.with(this).load(vm.uiMovie?.detailImageUrl).into(binding.detailImageIv)
        with(binding) {

            filmNameTv.text = vm.uiMovie?.title
            descriptionTv.text = vm.uiMovie?.overview
            //TODO add rating filling (new empty function)
//            setRatingStarsColor(vm.movie?.rating?:0)
            binding.reviewsCountTv.text =
                "${vm.uiMovie?.reviewCount} ${resources.getString(R.string.reviews_text)}"
            ageRateTv.text = "${vm.uiMovie?.age}+"
            Log.d("MYTAGUIMOVIE",vm.uiMovie.genres.toString())
            genreTv.text = Extra.getGenresText(vm.uiMovie?.genres)
        }
//        initActorsRecyclerView(movie?.actors)
    }

    private fun initActorsRecyclerView(actorsList: List<Actor>?) {
        binding.actorsRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.actorsRv.adapter = ActorsAdapter(actorsList)
    }

    private fun setRatingStarsColor(rating: Int) {
        val ratingIcons = listOf(
            binding.starImage1,
            binding.starImage2,
            binding.starImage3,
            binding.starImage4,
            binding.starImage5
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


    companion object {
        @JvmStatic
        fun newInstance(uiMovie: UiMovie): FragmentMovieDetails {
            val bundle = bundleOf(Pair(KEY_MOVIE, uiMovie))
            return FragmentMovieDetails().apply {
                this.arguments = bundle
            }
        }

        const val KEY_MOVIE = "key movie"
    }
}