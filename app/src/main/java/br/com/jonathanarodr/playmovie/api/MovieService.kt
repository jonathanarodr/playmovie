package br.com.jonathanarodr.playmovie.api

import br.com.jonathanarodr.playmovie.model.Movie
import br.com.jonathanarodr.playmovie.model.MovieReview
import br.com.jonathanarodr.playmovie.model.MovieVideo
import br.com.jonathanarodr.playmovie.model.ResultApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    companion object {
        const val URI_QUERY_API_KEY = "api_key"
    }

    @GET("popular")
    fun searchPopularMovies(@Query(URI_QUERY_API_KEY) apiKey: String): Call<ResultApi<Movie>>

    @GET("top_rated")
    fun searchTopRatedMovies(@Query(URI_QUERY_API_KEY) apiKey: String): Call<ResultApi<Movie>>

    @GET("{id}/videos")
    fun searchVideos(
        @Path("id") id: Int, @Query(URI_QUERY_API_KEY) apiKey: String
    ): Call<ResultApi<MovieVideo>>

    @GET("{id}/reviews")
    fun searchReviews(
        @Path("id") id: Int, @Query(URI_QUERY_API_KEY) apiKey: String
    ): Call<ResultApi<MovieReview>>
}