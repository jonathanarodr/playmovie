package br.com.jonathanarodr.playmovie.feature.domain.usecase

import br.com.jonathanarodr.playmovie.common.utils.asFlow
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.domain.model.toDetailUiModel
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepository
import br.com.jonathanarodr.playmovie.feature.ui.model.DetailUiModel
import kotlinx.coroutines.flow.Flow

// FIXME create a mapper patner to convert objetcs
class DetailUseCase(
    private val movieRepository: MovieRepository,
) {
    suspend fun getMovieDetail(id: Long): Flow<DetailUiModel> {
        val isFavorite = movieRepository.getFavoriteMovie(id).isSuccess

        return movieRepository.getMovieDetail(id).mapCatching { result ->
            result.toDetailUiModel(
                isFavorite = isFavorite,
            )
        }.asFlow()
    }

    suspend fun getTvSerieDetail(id: Long): Flow<DetailUiModel> {
        val isFavorite = movieRepository.getFavoriteMovie(id).isSuccess

        return movieRepository.getTvSerieDetail(id).mapCatching { result ->
            result.toDetailUiModel(
                isFavorite = isFavorite,
            )
        }.asFlow()
    }

    suspend fun insertFavoriteMovie(movie: Movie): Flow<Unit> {
        return movieRepository.insertFavoriteMovie(movie).asFlow()
    }

    suspend fun removeFavoriteMovie(movie: Movie): Flow<Unit> {
        return movieRepository.removeFavoriteMovie(movie).asFlow()
    }
}
