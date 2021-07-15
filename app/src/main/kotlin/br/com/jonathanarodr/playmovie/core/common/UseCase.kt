package br.com.jonathanarodr.playmovie.core.common

open class UseCase {

    suspend fun <T> execute(
        task: suspend () -> T,
    ): Result<T> {
        return runCatching {
            task()
        }
    }
}