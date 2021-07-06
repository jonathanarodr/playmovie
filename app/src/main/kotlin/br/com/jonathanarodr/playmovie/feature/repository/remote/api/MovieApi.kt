package br.com.jonathanarodr.playmovie.feature.repository.remote.api

import retrofit2.http.GET

interface MovieApi {

    @GET("/discover/movie")
    suspend fun searchMovies(): Result<MovieResponse>

    @GET("/discover/tv")
    suspend fun searchTvSeries(): Result<MovieResponse>
}