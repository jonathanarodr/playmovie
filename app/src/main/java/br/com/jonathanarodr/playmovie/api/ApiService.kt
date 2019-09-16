package br.com.jonathanarodr.playmovie.api

import br.com.jonathanarodr.playmovie.BuildConfig
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    @JvmStatic
    val instance: Retrofit get() = Builder()
        .baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}