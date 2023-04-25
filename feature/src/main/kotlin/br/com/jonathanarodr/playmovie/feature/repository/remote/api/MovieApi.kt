package br.com.jonathanarodr.playmovie.feature.repository.remote.api

import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("/3/movie/popular")
    suspend fun searchMovies(): MovieResponse

    @GET("/3/tv/popular")
    suspend fun searchTvSeries(): MovieResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: Long): Movie

    @GET("/3/tv/{tv_id}")
    suspend fun getTvSerieDetail(@Path("tv_id") id: Long): Movie
}
