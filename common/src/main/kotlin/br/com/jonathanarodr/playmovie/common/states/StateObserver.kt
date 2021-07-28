package br.com.jonathanarodr.playmovie.common.states

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<UiState<T>>.observeOnSuccess(
    owner: LifecycleOwner,
    handler: (T) -> Unit,
): LiveData<UiState<T>> {
    observe(owner) {
        if (it is UiState.Success) {
            handler(it.data)
        }
    }

    return this
}

fun <T> LiveData<UiState<T>>.observeOnError(
    owner: LifecycleOwner,
    handler: (Throwable) -> Unit,
): LiveData<UiState<T>> {
    observe(owner) {
        if (it is UiState.Error) {
            handler(it.cause)
        }
    }

    return this
}

fun <T> LiveData<UiState<T>>.observeOnEmpty(
    owner: LifecycleOwner,
    handler: () -> Unit,
): LiveData<UiState<T>> {
    observe(owner) {
        if (it is UiState.EmptyState) {
            handler()
        }
    }

    return this
}

fun <T> LiveData<UiState<T>>.observeOnLoading(
    owner: LifecycleOwner,
    handler: () -> Unit,
): LiveData<UiState<T>> {
    observe(owner) {
        if (it is UiState.Loading) {
            handler()
        }
    }

    return this
}