package br.com.jonathanarodr.playmovie.feature.repository.remote.api

import com.google.gson.annotations.SerializedName
import java.util.Date

data class MovieResponse(
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
)
