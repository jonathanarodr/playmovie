package br.com.jonathanarodr.playmovie.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    public static boolean isActiveNetwork(Context context) {
        Log.v(TAG, "Verificando conexão");

        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

}