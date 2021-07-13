package br.com.jonathanarodr.playmovie.core.common

import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class UseCase {

    @SuppressWarnings("TooGenericExceptionCaught")
    suspend fun <T> execute(
        context: CoroutineContext = EmptyCoroutineContext,
        task: suspend () -> T
    ) : Result<T> {

        return try {
            withContext(context) {
                Result.success(task.invoke())
            }
        } catch (ex: Exception) {
            Timber.e(ex, "Failed to execute the task")
            Result.failure(ex)
        }
    }
}