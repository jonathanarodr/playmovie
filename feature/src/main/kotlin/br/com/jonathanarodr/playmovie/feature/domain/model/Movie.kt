package br.com.jonathanarodr.playmovie.feature.domain.model

import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType
import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieEntity
import br.com.jonathanarodr.playmovie.feature.ui.model.DetailUiModel
import br.com.jonathanarodr.playmovie.feature.ui.model.MovieUiModel
import java.util.Date

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val poster: String?,
    val backdrop: String?,
    val average: Double,
    val releaseDate: Date,
    val type: MovieType,
)

fun Movie.toMovieEntity() = MovieEntity(
    id = this.id,
    title = this.title,
    overview = this.overview,
    poster = this.poster.orEmpty(),
    backdrop = this.backdrop.orEmpty(),
    average = this.average,
    releaseDate = this.releaseDate,
    type = this.type,
)

fun Movie.toMovieUiModel() = MovieUiModel(
    id = this.id,
    title = this.title,
    poster = this.poster.orEmpty(),
    average = this.average,
    releaseDate = this.releaseDate,
)

fun Movie.toDetailUiModel(isFavorite: Boolean) = DetailUiModel(
    id = this.id,
    title = this.title,
    overview = this.overview,
    poster = this.poster.orEmpty(),
    backdrop = this.backdrop.orEmpty(),
    average = this.average,
    releaseDate = this.releaseDate,
    type = this.type,
    isFavorite = isFavorite,
)
