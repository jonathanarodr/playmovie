package br.com.jonathanarodr.playmovie.common.executors

import br.com.jonathanarodr.playmovie.common.exception.ResultException
import kotlinx.coroutines.Dispatchers

open class StorageExecutor : CoroutinesExecutor(Dispatchers.IO) {

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
        return Result.failure(
            ResultException(
                errorType = ResultException.ResultError.STORAGE,
                message = "Unable to execute read or write on storage",
                cause = exception,
            )
        )
    }
}
