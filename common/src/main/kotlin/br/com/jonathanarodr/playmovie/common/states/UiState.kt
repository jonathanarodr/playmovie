package br.com.jonathanarodr.playmovie.common.states

@Deprecated("Create your own UI State instead")
sealed class UiState<out R> {

    data class Success<out T>(val data: T) : UiState<T>()

    data class Error(val cause: Throwable) : UiState<Nothing>()

    object Loading : UiState<Nothing>()

    object EmptyState : UiState<Nothing>()
}
