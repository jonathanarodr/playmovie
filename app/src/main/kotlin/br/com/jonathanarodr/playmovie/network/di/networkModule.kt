package br.com.jonathanarodr.playmovie.network.di

import br.com.jonathanarodr.playmovie.network.interceptor.AuthorizationInterceptor
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {

    factory {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    } bind Interceptor::class

    factory { AuthorizationInterceptor() } bind Interceptor::class
}