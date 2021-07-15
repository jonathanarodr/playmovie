package br.com.jonathanarodr.playmovie.feature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jonathanarodr.playmovie.core.common.UiState
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.domain.usecase.DetailUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val detailUseCase: DetailUseCase,
) : ViewModel() {

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _insertedMovie = MutableLiveData<UiState<Unit>>()
    val insertedMovie: LiveData<UiState<Unit>> = _insertedMovie

    private val _removedMovie = MutableLiveData<UiState<Unit>>()
    val removedMovie: LiveData<UiState<Unit>> = _removedMovie

    fun verifyFavoriteMovie(movieId: Long) {
        viewModelScope.launch {
            _isFavorite.value = detailUseCase.getFavoriteMovie(movieId).isSuccess
        }
    }

    fun insertFavoriteMovie(movie: Movie) {
        viewModelScope.launch {
            detailUseCase.insertFavoriteMovie(movie).onSuccess {
                _insertedMovie.value = UiState.Success(it)
            }.onFailure {
                _insertedMovie.value = UiState.Error(it)
            }
        }
    }

    fun removeFavoriteMovie(movie: Movie) {
        viewModelScope.launch {
            detailUseCase.removeFavoriteMovie(movie).onSuccess {
                _removedMovie.value = UiState.Success(it)
            }.onFailure {
                _removedMovie.value = UiState.Error(it)
            }
        }
    }
}