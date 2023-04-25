package br.com.jonathanarodr.playmovie.feature.repository.remote

import br.com.jonathanarodr.playmovie.common.executors.ApiExecutor
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.repository.remote.api.MovieApi

class MovieRemoteDataSource(
    private val movieApi: MovieApi,
) : ApiExecutor() {

    suspend fun searchMovies(): Result<List<Movie>> {
        return execute {
            movieApi.searchMovies().results
        }
    }

    suspend fun searchTvSeries(): Result<List<Movie>> {
        return execute {
            movieApi.searchTvSeries().results
        }
    }

    suspend fun getMovieDetail(id: Long): Result<Movie> {
        return execute {
            movieApi.getMovieDetail(id)
        }
    }

    suspend fun getTvSerieDetail(id: Long): Result<Movie> {
        return execute {
            movieApi.getTvSerieDetail(id)
        }
    }
}
