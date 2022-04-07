package com.projectapp.moviesapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.projectapp.moviesapp.R
import com.projectapp.moviesapp.domain.model.Actor
import com.projectapp.moviesapp.domain.model.Genre
import com.projectapp.moviesapp.domain.model.Movie
import com.projectapp.moviesapp.presentation.recyclerview.ActorsAdapter
import com.projectapp.moviesapp.databinding.FragmentMovieDetailsBinding
import com.projectapp.moviesapp.presentation.viewmodel.MovieDetailsViewModel
import com.projectapp.moviesapp.presentation.viewmodel.factory.MovieDetailsViewModelFactory

class FragmentMovieDetails : Fragment() {


    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val movie by lazy { arguments?.getParcelable<Movie>(KEY_MOVIE) }
    private val vm by lazy {
        ViewModelProvider(
            this,
            MovieDetailsViewModelFactory(movie)
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

    private fun fillDataToViews() {
        val movie = arguments?.getParcelable<Movie>(KEY_MOVIE)
        Glide.with(this).load(movie?.detailImageUrl).into(binding.detailImageIv)
        with(binding) {
            filmNameTv.text = movie?.title
            descriptionTv.text = movie?.storyLine
            //TODO add rating filling (new empty function)
            binding.reviewsCountTv.text =
                "${movie?.reviewCount} ${resources.getString(R.string.reviews_text)}"
            ageRateTv.text = "${movie?.pgAge}+"
            genreTv.text = vm.getGenresText()
        }
        initActorsRecyclerView(movie?.actors)
    }

    private fun initActorsRecyclerView(actorsList: List<Actor>?) {
        binding.actorsRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.actorsRv.adapter = ActorsAdapter(actorsList)
    }


    companion object {
        @JvmStatic
        fun newInstance(movie: Movie): FragmentMovieDetails {
            val bundle = bundleOf(Pair(KEY_MOVIE, movie))
            return FragmentMovieDetails().apply {
                this.arguments = bundle
            }
        }

        const val KEY_MOVIE = "key movie"
    }
}