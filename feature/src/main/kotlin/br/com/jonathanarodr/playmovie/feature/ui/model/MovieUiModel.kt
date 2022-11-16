package br.com.jonathanarodr.playmovie.feature.ui.model

import java.util.Date

data class MovieUiModel(
    val id: Long,
    val title: String,
    val poster: String,
    val average: Double,
    val releaseDate: Date,
)
