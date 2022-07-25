package br.com.jonathanarodr.playmovie.feature.ui.model

import java.util.*

data class MovieUiModel(
    val id: Long,
    val title: String,
    val posterUrl: String,
    val releaseDate: Date,
    val voteAverage: Double? = null,
)
