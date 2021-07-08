package br.com.jonathanarodr.playmovie.feature.domain.usecase

import br.com.jonathanarodr.playmovie.core.common.UseCase
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieUseCase(
    private val movieRepository: MovieRepository,
) : UseCase() {

    suspend fun searchMovies(): Result<List<Movie>> {
        return execute(Dispatchers.IO) {
            movieRepository.searchMovies()
        }
    }

    suspend fun searchTvSeries(): Result<List<Movie>> {
        return execute(Dispatchers.IO) {
            movieRepository.searchTvSeries()
        }
    }

    suspend fun fetchFavoriteMovies(): Result<List<Movie>> {
        return execute(Dispatchers.IO) {
            movieRepository.fetchFavoriteMovies()
        }
    }

    suspend fun insertFavoriteMovie(movie: Movie): Result<Unit> {
        return execute(Dispatchers.IO) {
            movieRepository.insertFavoriteMovie(movie)
        }
    }

    suspend fun removeFavoriteMovie(movie: Movie): Result<Unit> {
        return execute(Dispatchers.IO) {
            movieRepository.removeFavoriteMovie(movie)
        }
    }
}