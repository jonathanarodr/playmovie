package br.com.jonathanarodr.playmovie.network

import br.com.jonathanarodr.playmovie.network.builder.RetrofitBuilder
import org.koin.java.KoinJavaComponent.getKoin
import retrofit2.Retrofit

object NetworkClient {

    private lateinit var retrofit: Retrofit

    init {
        initMiddleware()
    }

    private fun initMiddleware() {
        val builder: RetrofitBuilder = getKoin().get()
        retrofit = builder.build()
    }

    fun <T> providerService(service: Class<T>): T {
        return retrofit.create(service)
    }
}
