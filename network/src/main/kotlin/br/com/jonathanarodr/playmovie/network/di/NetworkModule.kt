package br.com.jonathanarodr.playmovie.network.di

import br.com.jonathanarodr.playmovie.network.builder.OkHttpBuilder
import br.com.jonathanarodr.playmovie.network.builder.RetrofitBuilder
import br.com.jonathanarodr.playmovie.network.interceptor.AuthorizationInterceptor
import okhttp3.Interceptor
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {

    factory { AuthorizationInterceptor() } bind Interceptor::class

    factory { OkHttpBuilder(getAll()) }

    factory { RetrofitBuilder(get()) }
}
