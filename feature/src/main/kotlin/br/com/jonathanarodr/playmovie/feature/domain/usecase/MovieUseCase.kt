package br.com.jonathanarodr.playmovie.feature.domain.usecase

import br.com.jonathanarodr.playmovie.feature.domain.model.toMovieUiModel
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepository
import br.com.jonathanarodr.playmovie.feature.ui.model.MovieUiModel

// FIXME create a mapper patner to convert objetcs
class MovieUseCase(
    private val movieRepository: MovieRepository,
) {

    suspend fun searchMovies(): Result<List<MovieUiModel>> {
        return movieRepository.searchMovies().map { result ->
            result.map { it.toMovieUiModel() }
        }
    }

    suspend fun searchTvSeries(): Result<List<MovieUiModel>> {
        return movieRepository.searchTvSeries().map { result ->
            result.map { it.toMovieUiModel() }
        }
    }

    suspend fun searchFavoriteMovies(): Result<List<MovieUiModel>> {
        return movieRepository.searchFavoriteMovies().map { result ->
            result.map { it.toMovieUiModel() }
        }
    }
}
