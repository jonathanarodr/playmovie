package br.com.jonathanarodr.playmovie.common.exception

import okhttp3.internal.http2.ConnectionShutdownException
import java.net.ConnectException
import java.net.UnknownHostException

open class PlayMovieException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause)

class ResultException(
    errorType: ResultError = ResultError.UNKNOWN,
    message: String,
    cause: Throwable
) : PlayMovieException(message, cause) {

    var errorType: ResultError = errorType
        private set

    enum class ResultError {
        UNKNOWN,
        UNAVAILABLE_NETWORK,
        REQUEST_NETWORK,
        STORAGE,
    }
}

fun Throwable.isConnectionError(): Boolean {
    return this is ConnectException ||
            this is ConnectionShutdownException ||
            this is UnknownHostException
}