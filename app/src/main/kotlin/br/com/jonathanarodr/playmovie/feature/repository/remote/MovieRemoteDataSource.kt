package br.com.jonathanarodr.playmovie.feature.repository.remote

import br.com.jonathanarodr.playmovie.feature.domain.model.Movie

class MovieRemoteDataSource(
    private val movieService: MovieService,
) {

    suspend fun searchMovies(): Result<List<Movie>> {
        return movieService.searchMovies().map {
            it.results
        }
    }

    suspend fun searchTvSeries(): Result<List<Movie>> {
        return movieService.searchTvSeries().map {
            it.results
        }
    }
}