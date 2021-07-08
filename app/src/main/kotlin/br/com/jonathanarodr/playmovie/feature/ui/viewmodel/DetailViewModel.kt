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
    private val detailUseCase: DetailUseCase
) : ViewModel() {

    private val _resultAction = MutableLiveData<UiState<Unit>>()
    val resultAction: LiveData<UiState<Unit>> = _resultAction

    fun insertFavoriteMovie(movie: Movie) {
        viewModelScope.launch {
            detailUseCase.insertFavoriteMovie(movie).postResult()
        }
    }

    fun removeFavoriteMovie(movie: Movie) {
        viewModelScope.launch {
            detailUseCase.removeFavoriteMovie(movie).postResult()
        }
    }

    private fun Result<Unit>.postResult() {
        onSuccess {
            _resultAction.value = UiState.Success(it)
        }.onFailure {
            _resultAction.value = UiState.Error(it)
        }
    }
}