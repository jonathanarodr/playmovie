package br.com.jonathanarodr.playmovie.feature.ui.states

import br.com.jonathanarodr.playmovie.common.exception.ResultException
import br.com.jonathanarodr.playmovie.feature.ui.model.DetailUiModel

sealed interface DetailUiState {

    data class Success(val data: DetailUiModel) : DetailUiState

    object Loading : DetailUiState

    data class Error(val exception: ResultException) : DetailUiState

    object LikedMovie : DetailUiState

    object DislikedMovie : DetailUiState
}
