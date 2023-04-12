package br.com.jonathanarodr.playmovie.common.states

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

private const val SUBSCRIBED_TIMEOUT: Long = 5_000

fun <T> Flow<T>.stateWith(scope: CoroutineScope, initialValue: T) {
    this.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(SUBSCRIBED_TIMEOUT),
        initialValue = initialValue,
    )
}

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
