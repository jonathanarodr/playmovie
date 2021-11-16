package br.com.jonathanarodr.playmovie.feature.di

import br.com.jonathanarodr.playmovie.feature.domain.usecase.DetailUseCase
import br.com.jonathanarodr.playmovie.feature.domain.usecase.MovieUseCase
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepository
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepositoryImpl
import br.com.jonathanarodr.playmovie.feature.repository.local.MovieLocalDataSource
import br.com.jonathanarodr.playmovie.feature.repository.local.db.AppDataBase
import br.com.jonathanarodr.playmovie.feature.repository.remote.MovieRemoteDataSource
import br.com.jonathanarodr.playmovie.feature.repository.remote.api.MovieApi
import br.com.jonathanarodr.playmovie.feature.ui.viewmodel.DetailViewModel
import br.com.jonathanarodr.playmovie.feature.ui.viewmodel.MovieViewModel
import br.com.jonathanarodr.playmovie.network.NetworkClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {

    single { AppDataBase(androidApplication()) }
    single { get<AppDataBase>().movieDao() }
    single { NetworkClient.providerService(MovieApi::class.java) }
    single { MovieLocalDataSource(get()) }
    single { MovieRemoteDataSource(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    factory { MovieUseCase(get()) }
    factory { DetailUseCase(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
