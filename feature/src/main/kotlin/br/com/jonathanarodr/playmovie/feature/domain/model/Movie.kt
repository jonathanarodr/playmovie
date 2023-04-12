package br.com.jonathanarodr.playmovie.feature.domain.model

import android.os.Parcelable
import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieEntity
import br.com.jonathanarodr.playmovie.feature.ui.model.MovieUiModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title", alternate = ["name"])
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val poster: String?,
    @SerializedName("backdrop_path")
    val backdrop: String?,
    @SerializedName("vote_average")
    val average: Double,
    @SerializedName("release_date", alternate = ["first_air_date"])
    val releaseDate: Date,
) : Parcelable

fun Movie.toMovieEntity() = MovieEntity(
    id = this.id,
    title = this.title,
    overview = this.overview,
    poster = this.poster.orEmpty(),
    backdrop = this.backdrop.orEmpty(),
    average = this.average,
    releaseDate = this.releaseDate,
)

fun Movie.toMovieUiModel() = MovieUiModel(
    id = this.id,
    title = this.title,
    poster = this.poster.orEmpty(),
    average = this.average,
    releaseDate = this.releaseDate,
)
