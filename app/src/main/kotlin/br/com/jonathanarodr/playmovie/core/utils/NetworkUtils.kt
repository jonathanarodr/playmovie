package br.com.jonathanarodr.playmovie.core.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_VPN
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.os.Build
import androidx.annotation.RequiresApi
import timber.log.Timber

class NetworkUtils(
    private val connectivityManager: ConnectivityManager,
) {

    fun isActiveNetwork(): Boolean {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                isActiveNetworkApi29(connectivityManager)
            } else {
                isActiveNetwork(connectivityManager)
            }
        } catch (ex: SecurityException) {
            Timber.e(ex, "Failed to check connection")
            false
        }
    }

    @Suppress("DEPRECATION")
    private fun isActiveNetwork(connectivityManager: ConnectivityManager): Boolean {
        val activeNetwork = connectivityManager.activeNetworkInfo

        return activeNetwork?.isConnectedOrConnecting ?: false
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun isActiveNetworkApi29(connectivityManager: ConnectivityManager): Boolean {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        return capabilities?.run {
            hasTransport(TRANSPORT_CELLULAR)
                    || hasTransport(TRANSPORT_WIFI)
                    || hasTransport(TRANSPORT_VPN)
        } ?: false
    }
}