package br.com.jonathanarodr.playmovie.feature.repository.remote

import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.repository.remote.api.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRemoteDataSource(
    private val movieApi: MovieApi,
) {

    suspend fun searchMovies(): List<Movie> {
        return withContext(Dispatchers.IO) {
            movieApi.searchMovies().results
        }
    }

    suspend fun searchTvSeries(): List<Movie> {
        return withContext(Dispatchers.IO) {
            movieApi.searchTvSeries().results
        }
    }
}