package br.com.jonathanarodr.playmovie.common.usecase

open class UseCase {

    suspend fun <T> execute(
        task: suspend () -> T,
    ): Result<T> {
        return runCatching {
            task()
        }
    }
}
