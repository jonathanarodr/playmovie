package br.com.jonathanarodr.playmovie.startup

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import br.com.jonathanarodr.playmovie.common.di.commonModule
import br.com.jonathanarodr.playmovie.feature.di.featureModule
import br.com.jonathanarodr.playmovie.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

@Suppress("unused")
class KoinInitializer : Initializer<KoinApplication> {

    override fun create(context: Context): KoinApplication {
        Log.i("Startup", "Initializing koin component...")

        return startKoin {
            androidContext(context)
            modules(
                networkModule,
                commonModule,
                featureModule,
            )
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(LoggerInitializer::class.java)
    }
}
