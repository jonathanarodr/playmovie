package br.com.jonathanarodr.playmovie.core

import android.app.Application
import br.com.jonathanarodr.playmovie.BuildConfig
import br.com.jonathanarodr.playmovie.core.di.coreModule
import br.com.jonathanarodr.playmovie.feature.di.featureModule
import br.com.jonathanarodr.playmovie.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initDiModules()
        initLogger()
    }

    private fun initDiModules() {
        startKoin {
            androidContext(this@MainApplication)
            modules(
                coreModule,
                networkModule,
                featureModule,
            )
        }
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}