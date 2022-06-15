package br.com.jonathanarodr.playmovie.common.executors

import br.com.jonathanarodr.playmovie.common.exception.ResultException
import br.com.jonathanarodr.playmovie.common.exception.isConnectionError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class ApiExecutor(
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CoroutinesExecutor(dispatcher) {

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
        return if (exception.isConnectionError()) {
            Result.failure(
                ResultException(
                    errorType = ResultException.ResultError.UNAVAILABLE_NETWORK,
                    message = "Unavailable connection to execute request",
                    cause = exception,
                )
            )
        } else {
            Result.failure(
                ResultException(
                    errorType = ResultException.ResultError.REQUEST_NETWORK,
                    message = "Unable to execute api call",
                    cause = exception,
                )
            )
        }
    }
}
