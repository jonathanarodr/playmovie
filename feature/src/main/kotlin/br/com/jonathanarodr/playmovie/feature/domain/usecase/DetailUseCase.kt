package br.com.jonathanarodr.playmovie.feature.domain.usecase

import br.com.jonathanarodr.playmovie.common.utils.asFlow
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.domain.model.toDetailUiModel
import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepository
import br.com.jonathanarodr.playmovie.feature.ui.model.DetailUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

    suspend fun getFavoriteMovieDetail(id: Long): Flow<DetailUiModel> {
        return movieRepository.getFavoriteMovie(id).mapCatching { result ->
            result.toDetailUiModel(
                isFavorite = true,
            )
        }.asFlow()
    }

    suspend fun insertFavoriteMovie(id: Long, movieType: MovieType): Flow<Boolean> {
        return findMovie(id, movieType).map {
            movieRepository.insertFavoriteMovie(it).isSuccess
        }
    }

    suspend fun removeFavoriteMovie(id: Long, movieType: MovieType): Flow<Boolean> {
        return findMovie(id, movieType).map {
            movieRepository.removeFavoriteMovie(it).isSuccess
        }
    }

    // FIXME improve this logic with new 'FavoriteMovie' table to save movie ID instead of saving whole movie entity
    private suspend fun findMovie(id: Long, movieType: MovieType): Flow<Movie> {
        return if (movieType == MovieType.MOVIES) {
            movieRepository.getMovieDetail(id)
        } else {
            movieRepository.getTvSerieDetail(id)
        }.asFlow()
    }
}
