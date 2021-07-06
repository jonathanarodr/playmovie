package br.com.jonathanarodr.playmovie.feature.di

import br.com.jonathanarodr.playmovie.feature.repository.MovieRepositoryImpl
import br.com.jonathanarodr.playmovie.feature.repository.local.MovieLocalDataSource
import br.com.jonathanarodr.playmovie.feature.repository.local.db.AppDataBase
import br.com.jonathanarodr.playmovie.feature.repository.remote.MovieRemoteDataSource
import br.com.jonathanarodr.playmovie.feature.repository.remote.api.MovieApi
import br.com.jonathanarodr.playmovie.network.NetworkClientService
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val featureModule = module {

    single { AppDataBase(androidApplication()) }
    single { get<AppDataBase>().movieDao() }
    single { NetworkClientService.providerService(MovieApi::class.java) }
    single { MovieLocalDataSource(get()) }
    single { MovieRemoteDataSource(get()) }
    single { MovieRepositoryImpl(get(), get()) }
}