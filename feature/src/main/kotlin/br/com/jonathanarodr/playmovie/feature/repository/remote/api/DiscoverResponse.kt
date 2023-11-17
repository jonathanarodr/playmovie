package br.com.jonathanarodr.playmovie.feature.repository.remote.api

import com.google.gson.annotations.SerializedName

data class DiscoverResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val results: List<MovieResponse>,
    @SerializedName("total_results")
    val totalResults: Int,
)
