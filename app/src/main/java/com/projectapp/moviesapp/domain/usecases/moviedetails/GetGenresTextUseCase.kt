package com.projectapp.moviesapp.domain.usecases.moviedetails

import com.projectapp.moviesapp.domain.model.Genre

class GetGenresTextUseCase {
    fun execute(genres: List<Genre>?): String {
        if (genres.isNullOrEmpty()) return ""

        val sb = StringBuilder()
        for ((i, genre) in genres) {
            sb.append(genre)
            if (i < genres.size - 1) sb.append(", ")
        }
        return sb.toString()
    }
}