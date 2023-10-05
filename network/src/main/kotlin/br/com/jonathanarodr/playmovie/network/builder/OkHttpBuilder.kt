package br.com.jonathanarodr.playmovie.network.builder

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpBuilder(
    private val interceptors: List<Interceptor>,
) {

    companion object {
        private const val CLIENT_TIMEOUT = 60L
    }

    private fun OkHttpClient.Builder.addInterceptors() {
        interceptors.iterator().forEach {
            addInterceptor(it)
        }
    }

    private fun OkHttpClient.Builder.setTimeouts() {
        connectTimeout(CLIENT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(CLIENT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(CLIENT_TIMEOUT, TimeUnit.SECONDS)
    }

    fun build(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptors()
            setTimeouts()
        }.build()
    }
}
