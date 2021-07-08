package br.com.jonathanarodr.playmovie.feature.repository.remote.api

import retrofit2.http.GET

interface MovieApi {

    @GET("/4/discover/movie")
    suspend fun searchMovies(): MovieResponse

    @GET("/4/discover/tv")
    suspend fun searchTvSeries(): MovieResponse
}