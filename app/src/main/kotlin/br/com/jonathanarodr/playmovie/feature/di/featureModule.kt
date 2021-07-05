package br.com.jonathanarodr.playmovie.feature.di

import br.com.jonathanarodr.playmovie.feature.repository.remote.MovieRemoteDataSource
import br.com.jonathanarodr.playmovie.feature.repository.remote.MovieService
import br.com.jonathanarodr.playmovie.network.NetworkClientService
import org.koin.dsl.module

val featureModule = module {

    single { NetworkClientService.providerService(MovieService::class.java) }
    single { MovieRemoteDataSource(get()) }
}