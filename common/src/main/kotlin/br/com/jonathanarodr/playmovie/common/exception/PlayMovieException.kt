package br.com.jonathanarodr.playmovie.common.exception

import coil.network.HttpException
import okhttp3.internal.http2.ConnectionShutdownException
import java.net.ConnectException
import java.net.UnknownHostException

open class PlayMovieException(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message, cause)

class ResultException(
    val errorType: ResultError = ResultError.UNKNOWN,
    message: String,//fixme
    cause: Throwable,
) : PlayMovieException(message, cause) {

    var error: ResultError = errorType
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

fun Throwable.isRequestHttpError(): Boolean {
    return this is HttpException
}
