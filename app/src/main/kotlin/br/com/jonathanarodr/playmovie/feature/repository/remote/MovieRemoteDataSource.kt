package br.com.jonathanarodr.playmovie.feature.repository.remote

import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.repository.remote.api.MovieApi

class MovieRemoteDataSource(
    private val movieApi: MovieApi,
) {

    suspend fun searchMovies(): List<Movie> {
        return movieApi.searchMovies().results
    }

    suspend fun searchTvSeries(): List<Movie> {
        return movieApi.searchTvSeries().results
    }
}