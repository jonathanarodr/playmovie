package br.com.jonathanarodr.playmovie.feature.domain.usecase

import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepository

class DetailUseCase(
    private val movieRepository: MovieRepository,
) {

    suspend fun getFavoriteMovie(movieId: Long): Result<Movie> {
        return movieRepository.getFavoriteMovie(movieId)
    }

    suspend fun insertFavoriteMovie(movie: Movie): Result<Unit> {
        return movieRepository.insertFavoriteMovie(movie)
    }

    suspend fun removeFavoriteMovie(movie: Movie): Result<Unit> {
        return movieRepository.removeFavoriteMovie(movie)
    }
}
