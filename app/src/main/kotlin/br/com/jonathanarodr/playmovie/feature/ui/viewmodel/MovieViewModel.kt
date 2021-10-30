package br.com.jonathanarodr.playmovie.feature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jonathanarodr.playmovie.common.states.UiState
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType
import br.com.jonathanarodr.playmovie.feature.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieUseCase: MovieUseCase,
) : ViewModel() {

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
