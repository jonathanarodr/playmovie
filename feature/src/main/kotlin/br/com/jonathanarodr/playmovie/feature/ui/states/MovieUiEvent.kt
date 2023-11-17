package br.com.jonathanarodr.playmovie.feature.ui.states

import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType

sealed interface MovieUiEvent {

    data class PullToRefresh(val movieType: MovieType) : MovieUiEvent
}
