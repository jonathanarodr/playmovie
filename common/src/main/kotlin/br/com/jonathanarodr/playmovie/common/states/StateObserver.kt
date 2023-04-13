package br.com.jonathanarodr.playmovie.common.states

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

@Deprecated("Use StateFlow with ViewModelState instead")
inline fun <T> LiveData<UiState<T>>.observeOnSuccess(
    owner: LifecycleOwner,
    crossinline handler: (T) -> Unit,
): LiveData<UiState<T>> {
    observe(owner) {
        if (it is UiState.Success) {
            handler(it.data)
        }
    }

    return this
}

@Deprecated("Use StateFlow with ViewModelState instead")
inline fun <T> LiveData<UiState<T>>.observeOnSuccess(
    owner: LifecycleOwner,
    crossinline handler: () -> Unit,
): LiveData<UiState<T>> {
    observe(owner) {
        if (it is UiState.Success) {
            handler()
        }
    }

    return this
}

@Deprecated("Use StateFlow with ViewModelState instead")
inline fun <T> LiveData<UiState<T>>.observeOnError(
    owner: LifecycleOwner,
    crossinline handler: (Throwable) -> Unit,
): LiveData<UiState<T>> {
    observe(owner) {
        if (it is UiState.Error) {
            handler(it.cause)
        }
    }

    return this
}

@Deprecated("Use StateFlow with ViewModelState instead")
inline fun <T> LiveData<UiState<T>>.observeOnEmpty(
    owner: LifecycleOwner,
    crossinline handler: () -> Unit,
): LiveData<UiState<T>> {
    observe(owner) {
        if (it is UiState.EmptyState) {
            handler()
        }
    }

    return this
}

@Deprecated("Use StateFlow with ViewModelState instead")
inline fun <T> LiveData<UiState<T>>.observeOnLoading(
    owner: LifecycleOwner,
    crossinline handler: () -> Unit,
): LiveData<UiState<T>> {
    observe(owner) {
        if (it is UiState.Loading) {
            handler()
        }
    }

    return this
}
