package br.com.jonathanarodr.playmovie.feature.repository.remote.api

import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val results: List<Movie>,
    @SerializedName("total_results")
    val totalResults: Int,
)
