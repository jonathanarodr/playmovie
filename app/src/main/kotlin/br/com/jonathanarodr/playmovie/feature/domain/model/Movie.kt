package br.com.jonathanarodr.playmovie.feature.domain.model

import br.com.jonathanarodr.playmovie.feature.repository.local.database.MovieEntity
import com.google.gson.annotations.SerializedName
import java.util.*

data class Movie(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title", alternate = ["name"])
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("backdrop_path")
    val backdrop: String,
    @SerializedName("vote_average")
    val average: Double,
    @SerializedName("release_date", alternate = ["first_air_date"])
    val releaseDate: Date,
)

fun Movie.toMovieEntity() = MovieEntity(
    id = this.id,
    title = this.title,
    overview = this.overview,
    poster = this.poster,
    backdrop = this.backdrop,
    average = this.average,
    releaseDate = this.releaseDate,
)