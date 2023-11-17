package br.com.jonathanarodr.playmovie.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale

class LanguageInterceptor : Interceptor {

    private val headerKey = "language"
    private val locale = Locale.getDefault()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header(headerKey, locale.language)
            .build()

        return chain.proceed(request)
    }
}
