package br.com.jonathanarodr.playmovie.network.interceptor

import br.com.jonathanarodr.playmovie.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    companion object {
        private const val AUTHORIZATION_KEY = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()
            .addHeader(AUTHORIZATION_KEY, "Bearer ${BuildConfig.AUTHORIZATION_KEY}")
            .build()

        return chain.proceed(request)
    }
}