package br.com.jonathanarodr.playmovie.common.exception

import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

open class PlayMovieException(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message, cause)

enum class ResultError {
    UNKNOWN,
    UNAVAILABLE_NETWORK,
    REQUEST_NETWORK,
    STORAGE,
}

data class ResultException(
    val errorType: ResultError = ResultError.UNKNOWN,
    override val message: String,
    override val cause: Throwable,
) : PlayMovieException(message, cause)

fun Throwable.isConnectionError(): Boolean {
    return this is ConnectException ||
        this is ConnectionShutdownException ||
        this is UnknownHostException
}

fun Throwable.isRequestHttpError(): Boolean {
    return this is HttpException
}
