package com.projectapp.moviesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.projectapp.moviesapp.data.JsonMovieRepository
import com.projectapp.moviesapp.data.MovieRepository
import com.projectapp.moviesapp.data.model.Actor
import com.projectapp.moviesapp.data.model.Genre
import com.projectapp.moviesapp.data.model.Movie
import com.projectapp.moviesapp.my_data.ActorsAdapter
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
        Glide.with(this).load(movie?.detailImageUrl).into(binding.detailImageIv)
        with(binding) {
            filmNameTv.text = movie?.title
            storylineText.text = movie?.storyLine
            //TODO add rating filling (new empty function)
            binding.reviewsCountTv.text =
                "${movie?.reviewCount} ${resources.getString(R.string.reviews_text)}"
            ageRateTv.text = "${movie?.pgAge}+"
            genreTv.text = getGenresTextViewText(movie?.genres ?: emptyList())
        }
        initActorsRecyclerView(movie?.actors)
    }

    private fun initActorsRecyclerView(actorsList: List<Actor>?) {
        binding.actorsRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.actorsRv.adapter = ActorsAdapter(actorsList ?: emptyList())
    }


    companion object {
        @JvmStatic
        fun newInstance(movie: Movie): FragmentMovieDetails {
            val bundle = bundleOf(Pair(KEY_MOVIE, movie))
            return FragmentMovieDetails().apply {
                this.arguments = bundle
            }
        }

        //viewModel function
        fun getGenresTextViewText(genres: List<Genre>): String {
            val sb = StringBuilder()
            for ((i, genre) in genres) {
                sb.append(genre)
                if (i < genres.size - 1) sb.append(", ")
            }
            return sb.toString()
        }

        const val KEY_MOVIE = "key movie"
    }
}