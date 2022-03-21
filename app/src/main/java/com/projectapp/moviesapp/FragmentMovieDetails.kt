package com.projectapp.moviesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.projectapp.moviesapp.data.Actor
import com.projectapp.moviesapp.data.ActorsAdapter
import com.projectapp.moviesapp.data.Movie
import com.projectapp.moviesapp.databinding.FragmentMovieDetailsBinding

class FragmentMovieDetails : Fragment() {


    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = requireNotNull(_binding)

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
        with(binding) {
            filmNameTv.text = movie?.filmName
            binding.reviewsCountTv.text =
                "${movie?.reviewsCount} ${resources.getString(R.string.reviews_text)}"
            genreTv.text = movie?.genres
            ageRateTv.text = "${movie?.ageRate}+"
            storylineText.text = movie?.storyLine
        }
        setActorsRecyclerView(movie?.actorsList)
    }

    private fun setActorsRecyclerView(actorsList: List<Actor>?) {
        binding.actorsRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.actorsRv.adapter = ActorsAdapter(actorsList?: emptyList())
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