package com.projectapp.moviesapp.data.model.original

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class JsonActorOriginal(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val profilePicture: String
)