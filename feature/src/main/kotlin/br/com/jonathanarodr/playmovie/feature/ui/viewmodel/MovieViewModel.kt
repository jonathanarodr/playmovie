package br.com.jonathanarodr.playmovie.feature.ui.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.jonathanarodr.playmovie.common.exception.ResultException
import br.com.jonathanarodr.playmovie.common.states.ViewModelState
import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType
import br.com.jonathanarodr.playmovie.feature.domain.usecase.MovieUseCase
import br.com.jonathanarodr.playmovie.feature.ui.states.MovieUiEvent
import br.com.jonathanarodr.playmovie.feature.ui.states.MovieUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieUseCase: MovieUseCase,
) : ViewModelState<MovieUiState, MovieUiEvent>() {

    override val uiState: MutableStateFlow<MovieUiState> = MutableStateFlow(MovieUiState.Loading)

    override fun dispatchUiEvent(uiEvent: MovieUiEvent) {
        when (uiEvent) {
            is MovieUiEvent.PullToRefresh -> fetchMovies(uiEvent.movieType)
        }
    }

    private fun fetchMovies(movieType: MovieType) {
        uiState.value = MovieUiState.Loading

        viewModelScope.launch {
            when (movieType) {
                MovieType.MOVIES -> movieUseCase.searchMovies()
                MovieType.SERIES -> movieUseCase.searchTvSeries()
                MovieType.FAVORITES -> movieUseCase.searchFavoriteMovies()
            }.map { result ->
                uiState.value = if (result.isEmpty()) {
                    MovieUiState.Empty
                } else {
                    MovieUiState.Success(result)
                }
            }.catch {
                uiState.value = MovieUiState.Error(it as ResultException)
            }.collect()
        }
    }
}
