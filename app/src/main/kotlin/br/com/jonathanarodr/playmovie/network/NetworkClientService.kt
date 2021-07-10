package br.com.jonathanarodr.playmovie.network

import br.com.jonathanarodr.playmovie.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.java.KoinJavaComponent.getKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkClientService {

    private const val NETWORK_SERVICE_TIMEOUT = 60L
    private lateinit var retrofit: Retrofit

    init {
        initMiddleware()
    }

    private fun buildClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        val interceptors = getKoin().getAll<Interceptor>()

        interceptors.forEach {
            okHttpClient.addInterceptor(it)
        }

        return okHttpClient.apply {
            connectTimeout(NETWORK_SERVICE_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(NETWORK_SERVICE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(NETWORK_SERVICE_TIMEOUT, TimeUnit.SECONDS)
        }.build()
    }

    private fun initMiddleware() {
        val okHttpClient = buildClient()

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