package br.com.jonathanarodr.playmovie.feature.domain.usecase

import br.com.jonathanarodr.playmovie.feature.mapper.toMovieUiModel
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepository
import br.com.jonathanarodr.playmovie.feature.ui.model.MovieUiModel

class MovieUseCase(
    private val movieRepository: MovieRepository,
) {

    suspend fun searchMovies(): Result<List<MovieUiModel>> {
        return movieRepository.searchMovies().mapCatching { result ->
            result.map { it.toMovieUiModel() }
        }
    }

    suspend fun searchTvSeries(): Result<List<MovieUiModel>> {
        return movieRepository.searchTvSeries().mapCatching { result ->
            result.map { it.toMovieUiModel() }
        }
    }

    suspend fun searchFavoriteMovies(): Result<List<MovieUiModel>> {
        return movieRepository.searchFavoriteMovies().mapCatching { result ->
            result.map { it.toMovieUiModel() }
        }
    }
}
