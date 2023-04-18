package br.com.jonathanarodr.playmovie.feature.domain.usecase

import br.com.jonathanarodr.playmovie.common.utils.asFlow
import br.com.jonathanarodr.playmovie.feature.domain.model.toMovieUiModel
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepository
import br.com.jonathanarodr.playmovie.feature.ui.model.MovieUiModel
import kotlinx.coroutines.flow.Flow

// FIXME create a mapper patner to convert objetcs
class MovieUseCase(
    private val movieRepository: MovieRepository,
) {

    suspend fun searchMovies(): Flow<List<MovieUiModel>> {
        return movieRepository.searchMovies().map { result ->
            result.map { it.toMovieUiModel() }
        }.asFlow()
    }

    suspend fun searchTvSeries(): Flow<List<MovieUiModel>> {
        return movieRepository.searchTvSeries().map { result ->
            result.map { it.toMovieUiModel() }
        }.asFlow()
    }

    suspend fun searchFavoriteMovies(): Flow<List<MovieUiModel>> {
        return movieRepository.searchFavoriteMovies().map { result ->
            result.map { it.toMovieUiModel() }
        }.asFlow()
    }
}
