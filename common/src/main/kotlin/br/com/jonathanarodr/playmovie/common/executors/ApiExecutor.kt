package br.com.jonathanarodr.playmovie.common.executors

import br.com.jonathanarodr.playmovie.common.exception.ResultError
import br.com.jonathanarodr.playmovie.common.exception.ResultException
import br.com.jonathanarodr.playmovie.common.exception.isConnectionError
import br.com.jonathanarodr.playmovie.common.exception.isRequestHttpError
import kotlinx.coroutines.Dispatchers

open class ApiExecutor : CoroutinesExecutor(Dispatchers.IO) {

    public override suspend fun <T> execute(task: suspend () -> T): Result<T> {
        return super.execute(task).fold(
            onSuccess = ::onSuccess,
            onFailure = ::onFailure
        )
    }

    private fun <T> onSuccess(value: T): Result<T> {
        return Result.success(value)
    }

    private fun <T> onFailure(exception: Throwable): Result<T> {
        return when {
            exception.isConnectionError() -> {
                Result.failure(
                    ResultException(
                        errorType = ResultError.UNAVAILABLE_NETWORK,
                        message = "Unavailable connection to execute request",
                        cause = exception,
                    )
                )
            }
            exception.isRequestHttpError() -> {
                Result.failure(
                    ResultException(
                        errorType = ResultError.REQUEST_NETWORK,
                        message = "Unable to execute api call",
                        cause = exception,
                    )
                )
            }
            else -> {
                Result.failure(
                    ResultException(
                        errorType = ResultError.UNKNOWN,
                        message = "Unknown error when executing api call",
                        cause = exception,
                    )
                )
            }
        }
    }
}
