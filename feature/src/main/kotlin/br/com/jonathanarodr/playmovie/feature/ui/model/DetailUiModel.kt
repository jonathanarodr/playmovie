package br.com.jonathanarodr.playmovie.feature.ui.model

import java.util.Date

data class DetailUiModel(
    val id: Long,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val average: Double,
    val releaseDate: Date,
    val isFavorite: Boolean,
)
