package br.com.jonathanarodr.playmovie.core

import android.app.Application
import br.com.jonathanarodr.playmovie.BuildConfig
import br.com.jonathanarodr.playmovie.core.di.coreModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainApplication : Application() {

    companion object {
        const val NETWORK_SERVICE_TIMEOUT = 60L
    }

    override fun onCreate() {
        super.onCreate()

        initDiModules()
        initLogger()
        initNetwork()
    }

    private fun initDiModules() {
        startKoin {
            androidContext(this@MainApplication)
            modules(
                coreModule,
            )
        }
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initNetwork() {
        val okHttpClient = OkHttpClient.Builder()
        val logInterceptor = HttpLoggingInterceptor()

        okHttpClient.apply {
            addInterceptor(logInterceptor)
            connectTimeout(NETWORK_SERVICE_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(NETWORK_SERVICE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(NETWORK_SERVICE_TIMEOUT, TimeUnit.SECONDS)
                .build()
        }
    }
}