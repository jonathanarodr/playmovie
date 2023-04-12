package br.com.jonathanarodr.playmovie.common.utils

import br.com.jonathanarodr.playmovie.common.exception.ResultException
import timber.log.Timber

inline fun <T> Result<T>.onFailure(action: (ResultException) -> Unit): Result<T> {
    val exception = getOrNull() as? ResultException

    if (exception != null) {
        action(exception)
    } else {
        Timber.e("Could not retrieve the result exception with: Result ${getOrNull()}")
    }

    return this
}
