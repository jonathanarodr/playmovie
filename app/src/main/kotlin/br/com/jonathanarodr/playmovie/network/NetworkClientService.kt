package br.com.jonathanarodr.playmovie.network

import br.com.jonathanarodr.playmovie.BuildConfig
import br.com.jonathanarodr.playmovie.network.interceptor.AuthorizationInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkClientService {

    private const val NETWORK_SERVICE_TIMEOUT = 60L
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit

    init {
        initClient()
        initService()
    }

    private fun initClient() {
        val loggerInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(loggerInterceptor)
            addInterceptor(AuthorizationInterceptor())
            connectTimeout(NETWORK_SERVICE_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(NETWORK_SERVICE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(NETWORK_SERVICE_TIMEOUT, TimeUnit.SECONDS)
        }.build()
    }

    private fun initService() {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun <T> providerService(service: Class<T>) : T {
        return retrofit.create(service)
    }
}