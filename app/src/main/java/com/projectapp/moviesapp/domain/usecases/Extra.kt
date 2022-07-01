package com.projectapp.moviesapp.domain.usecases

import com.projectapp.moviesapp.data.model.Genre

object Extra {
    fun getGenresText(genres: List<Genre>?): String {
        if (genres.isNullOrEmpty()) return ""

        val sb = StringBuilder()
        genres.forEachIndexed { i, genre ->
            sb.append(genre.name)
            if (i < genres.size - 1) {
                sb.append(", ")
            }
        }
        return sb.toString()
    }
}