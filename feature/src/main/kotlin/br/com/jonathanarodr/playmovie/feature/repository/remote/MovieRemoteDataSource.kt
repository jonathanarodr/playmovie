package br.com.jonathanarodr.playmovie.feature.repository.remote

import br.com.jonathanarodr.playmovie.common.executors.ApiExecutor
import br.com.jonathanarodr.playmovie.feature.repository.remote.api.MovieApi
import br.com.jonathanarodr.playmovie.feature.repository.remote.api.MovieResponse

class MovieRemoteDataSource(
    private val movieApi: MovieApi,
) : ApiExecutor() {

    suspend fun searchMovies(): Result<List<MovieResponse>> {
        return execute {
            movieApi.searchMovies().results
        }
    }

    suspend fun searchTvSeries(): Result<List<MovieResponse>> {
        return execute {
            movieApi.searchTvSeries().results
        }
    }

    suspend fun getMovieDetail(id: Long): Result<MovieResponse> {
        return execute {
            movieApi.getMovieDetail(id)
        }
    }

    suspend fun getTvSerieDetail(id: Long): Result<MovieResponse> {
        return execute {
            movieApi.getTvSerieDetail(id)
        }
    }
}
