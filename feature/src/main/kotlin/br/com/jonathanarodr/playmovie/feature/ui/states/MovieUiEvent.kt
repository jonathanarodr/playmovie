package br.com.jonathanarodr.playmovie.feature.ui.states

import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType

sealed interface MovieUiEvent {

    data class Init(val movieType: MovieType) : MovieUiEvent

    object PullToRefresh : MovieUiEvent

    data class OpenMovieDetail(val movieId: String) : MovieUiEvent
}
