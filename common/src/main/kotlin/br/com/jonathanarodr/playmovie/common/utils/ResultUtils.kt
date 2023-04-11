package br.com.jonathanarodr.playmovie.common.utils

import br.com.jonathanarodr.playmovie.common.exception.ResultException

inline fun <T> Result<T>.onFailure(action: (exception: ResultException) -> Unit): Result<T> {
    getOrNull()?.let { action(it as ResultException) }
    return this
}
