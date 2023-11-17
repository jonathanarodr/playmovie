package br.com.jonathanarodr.playmovie.feature.repository.remote.api

import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("/3/movie/popular")
    suspend fun searchMovies(): DiscoverResponse

    @GET("/3/tv/popular")
    suspend fun searchTvSeries(): DiscoverResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: Long): MovieResponse

    @GET("/3/tv/{tv_id}")
    suspend fun getTvSerieDetail(@Path("tv_id") id: Long): MovieResponse
}
