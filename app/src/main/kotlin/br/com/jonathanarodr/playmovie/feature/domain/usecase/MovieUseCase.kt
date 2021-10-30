package br.com.jonathanarodr.playmovie.feature.domain.usecase

import br.com.jonathanarodr.playmovie.common.usecase.UseCase
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepository

class MovieUseCase(
    private val movieRepository: MovieRepository,
) : UseCase() {

    suspend fun searchMovies(): Result<List<Movie>> {
        return execute {
            movieRepository.searchMovies()
        }
    }

    suspend fun searchTvSeries(): Result<List<Movie>> {
        return execute {
            movieRepository.searchTvSeries()
        }
    }

    suspend fun searchFavoriteMovies(): Result<List<Movie>> {
        return execute {
            movieRepository.searchFavoriteMovies()
        }
    }
}
