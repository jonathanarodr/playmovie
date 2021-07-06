package br.com.jonathanarodr.playmovie.feature.repository.remote

import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.repository.remote.api.MovieApi

class MovieRemoteDataSource(
    private val movieApi: MovieApi,
) {

    suspend fun searchMovies(): Result<List<Movie>> {
        return movieApi.searchMovies().map {
            it.results
        }
    }

    suspend fun searchTvSeries(): Result<List<Movie>> {
        return movieApi.searchTvSeries().map {
            it.results
        }
    }
}