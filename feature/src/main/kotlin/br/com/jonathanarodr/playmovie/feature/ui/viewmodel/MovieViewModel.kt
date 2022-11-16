package br.com.jonathanarodr.playmovie.feature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.jonathanarodr.playmovie.common.base.ViewModelState
import br.com.jonathanarodr.playmovie.common.states.UiState
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType
import br.com.jonathanarodr.playmovie.feature.domain.usecase.MovieUseCase
import br.com.jonathanarodr.playmovie.feature.ui.states.MovieUiEvent
import br.com.jonathanarodr.playmovie.feature.ui.states.MovieUiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    override val uiState: StateFlow<MovieUiState>,
    private val movieUseCase: MovieUseCase,
) : ViewModelState<MovieUiState, MovieUiEvent>() {

    override fun dispatchUiEvent(uiEvent: MovieUiEvent) {
        when (uiEvent) {
            is MovieUiEvent.Init -> {}
            is MovieUiEvent.PullToRefresh -> {}
            is MovieUiEvent.OpenMovieDetail -> {}
        }
    }

    private val _fetchMovies = MutableLiveData<UiState<List<Movie>>>()
    val fetchMovies: LiveData<UiState<List<Movie>>> = _fetchMovies

    fun fetchMovies(movieType: MovieType) {
        _fetchMovies.value = UiState.Loading

        viewModelScope.launch {
            when (movieType) {
                MovieType.MOVIES -> movieUseCase.searchMovies()
                MovieType.SERIES -> movieUseCase.searchTvSeries()
                MovieType.FAVORITES -> movieUseCase.searchFavoriteMovies()
            }.postResult()
        }
    }

    private fun Result<List<Movie>>.postResult() {
        this.onSuccess {
            _fetchMovies.value = if (it.isNotEmpty()) {
                UiState.Success(it)
            } else {
                UiState.EmptyState
            }
        }.onFailure {
            _fetchMovies.value = UiState.Error(it)
        }
    }
}
