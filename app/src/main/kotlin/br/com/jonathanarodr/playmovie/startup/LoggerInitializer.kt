package br.com.jonathanarodr.playmovie.startup

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import br.com.jonathanarodr.playmovie.BuildConfig
import timber.log.Timber

class LoggerInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Log.i("Startup", "Initializing logger component...")

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
