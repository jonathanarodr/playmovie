package br.com.jonathanarodr.playmovie.feature.ui.states

sealed interface DetailUiEvent {

    object Init : DetailUiEvent

    object InsertFavorite : DetailUiEvent

    object RemoveFavorite : DetailUiEvent
}
