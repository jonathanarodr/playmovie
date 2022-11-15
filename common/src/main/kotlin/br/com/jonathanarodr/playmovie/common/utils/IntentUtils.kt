package br.com.jonathanarodr.playmovie.common.utils

import android.content.Intent
import android.os.Build

@Suppress("DEPRECATION")
inline fun <reified T> Intent.getParcelableExtraCompat(name: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(name, T::class.java)
    } else {
        getParcelableExtra(name)
    }
}
