package br.com.jonathanarodr.playmovie.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log

object NetworkUtils {
    private val TAG = NetworkUtils::class.java.simpleName

    @JvmStatic
    @SuppressLint("NewApi")
    fun hasNetworkConnection(context: Context): Boolean {
        Log.v(TAG, "Verificando conexão")

        val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}