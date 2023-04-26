package br.com.jonathanarodr.playmovie.feature.ui.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.jonathanarodr.playmovie.common.exception.ResultException
import br.com.jonathanarodr.playmovie.common.states.ViewModelState
import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType
import br.com.jonathanarodr.playmovie.feature.domain.usecase.DetailUseCase
import br.com.jonathanarodr.playmovie.feature.ui.model.DetailUiModel
import br.com.jonathanarodr.playmovie.feature.ui.states.DetailUiEvent
import br.com.jonathanarodr.playmovie.feature.ui.states.DetailUiState
import br.com.jonathanarodr.playmovie.feature.ui.view.MovieSafeArgs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailViewModel(
    private val movieSafeArgs: MovieSafeArgs,
    private val detailUseCase: DetailUseCase,
) : ViewModelState<DetailUiState, DetailUiEvent>() {

    override val uiState: MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState.Loading)

    override fun dispatchUiEvent(uiEvent: DetailUiEvent) {
        when (uiEvent) {
            is DetailUiEvent.Init -> getMovieDetail()
            is DetailUiEvent.InsertFavorite -> insertFavoriteMovie()
            is DetailUiEvent.RemoveFavorite -> removeFavoriteMovie()
        }
    }

    private suspend fun findMovie(): Flow<DetailUiModel> {
        return when (movieSafeArgs.type) {
            MovieType.MOVIES -> detailUseCase.getMovieDetail(movieSafeArgs.id)
            MovieType.SERIES -> detailUseCase.getTvSerieDetail(movieSafeArgs.id)
            MovieType.FAVORITES -> detailUseCase.getFavoriteMovieDetail(movieSafeArgs.id)
        }
    }

    private fun getMovieDetail() {
        uiState.value = DetailUiState.Loading

        viewModelScope.launch {
            findMovie().onEach {
                uiState.value = DetailUiState.Success(it)
            }.catch {
                uiState.value = DetailUiState.Error(it as ResultException)
            }.distinctUntilChanged()
        }
    }

    private fun insertFavoriteMovie() {
        uiState.value = DetailUiState.LikedMovie

        viewModelScope.launch {
            detailUseCase.insertFavoriteMovie(movieSafeArgs.id, movieSafeArgs.type).catch {
                uiState.value = DetailUiState.DislikedMovie
                uiState.value = DetailUiState.Error(it as ResultException)
            }
        }
    }

    private fun removeFavoriteMovie() {
        uiState.value = DetailUiState.DislikedMovie

        viewModelScope.launch {
            detailUseCase.removeFavoriteMovie(movieSafeArgs.id, movieSafeArgs.type).catch {
                uiState.value = DetailUiState.LikedMovie
                uiState.value = DetailUiState.Error(it as ResultException)
            }
        }
    }
}
