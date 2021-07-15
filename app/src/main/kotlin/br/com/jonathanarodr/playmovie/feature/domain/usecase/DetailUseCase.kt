package br.com.jonathanarodr.playmovie.feature.domain.usecase

import br.com.jonathanarodr.playmovie.common.usecase.UseCase
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepository

class DetailUseCase(
    private val movieRepository: MovieRepository,
) : UseCase() {

    suspend fun getFavoriteMovie(movieId: Long): Result<Movie> {
        return execute {
            movieRepository.getFavoriteMovie(movieId)
        }
    }

    suspend fun insertFavoriteMovie(movie: Movie): Result<Unit> {
        return execute {
            movieRepository.insertFavoriteMovie(movie)
        }
    }

    suspend fun removeFavoriteMovie(movie: Movie): Result<Unit> {
        return execute {
            movieRepository.removeFavoriteMovie(movie)
        }
    }
}