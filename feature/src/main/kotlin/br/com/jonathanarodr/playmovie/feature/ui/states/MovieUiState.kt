package br.com.jonathanarodr.playmovie.feature.ui.states

import br.com.jonathanarodr.playmovie.common.exception.ResultException
import br.com.jonathanarodr.playmovie.feature.ui.model.MovieUiModel

sealed interface MovieUiState {

    data class Success(val data: List<MovieUiModel>) : MovieUiState

    object Empty : MovieUiState

    object Loading : MovieUiState

    data class Error(val exception: ResultException) : MovieUiState
}
