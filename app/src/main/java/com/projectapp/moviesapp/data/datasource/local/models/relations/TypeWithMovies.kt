package com.projectapp.moviesapp.data.datasource.local.models.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.projectapp.moviesapp.data.datasource.local.models.MovieEntity
import com.projectapp.moviesapp.data.datasource.local.models.MovieToTypeEntity
import com.projectapp.moviesapp.data.datasource.local.models.MovieTypeEntity
import com.projectapp.moviesapp.domain.usecases.movielist.MovieType

data class TypeWithMovies(
    @Embedded
    val movieType: MovieTypeEntity,

    @Relation(
        parentColumn = "type_name",
        entityColumn = "id",
        associateBy = Junction(MovieToTypeEntity::class)
    )
    val moviesList: List<MovieEntity>,
)
