package br.com.jonathanarodr.playmovie.network.builder

import br.com.jonathanarodr.playmovie.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder(
    private val okHttpBuilder: OkHttpBuilder,
) {

    fun build(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }
}