package br.com.jonathanarodr.playmovie.feature.repository.remote

import retrofit2.http.GET

interface MovieService {

    @GET("/discover/movie")
    suspend fun searchMovies(): Result<MovieResponse>

    @GET("/discover/tv")
    suspend fun searchTvSeries(): Result<MovieResponse>
}