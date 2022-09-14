package br.com.jonathanarodr.playmovie.feature.mapper

import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieEntity
import br.com.jonathanarodr.playmovie.feature.ui.model.MovieUiModel

fun Movie.toMovieEntity() = MovieEntity(
    id = this.id,
    title = this.title,
    overview = this.overview,
    poster = this.poster ?: "",
    backdrop = this.backdrop ?: "",
    average = this.average,
    releaseDate = this.releaseDate,
)

fun Movie.toMovieUiModel() = MovieUiModel(
    id = this.id,
    title = this.title,
    posterUrl = this.poster ?: "",
    releaseDate = this.releaseDate,
    voteAverage = this.average,
)

fun MovieEntity.toMovie() = Movie(
    id = this.id,
    title = this.title,
    overview = this.overview,
    poster = this.poster,
    backdrop = this.backdrop,
    average = this.average,
    releaseDate = this.releaseDate,
)