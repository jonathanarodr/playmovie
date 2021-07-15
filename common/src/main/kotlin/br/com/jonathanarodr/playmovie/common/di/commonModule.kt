package br.com.jonathanarodr.playmovie.common.di

import android.content.Context
import android.net.ConnectivityManager
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val commonModule = module {

    factory { androidContext().resources }
    factory { androidApplication().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
}