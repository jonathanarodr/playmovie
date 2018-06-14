package br.com.jonathanarodr.playmovie.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.com.jonathanarodr.playmovie.BuildConfig;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String URI_QUERY_API_KEY = "api_key";

    public static boolean isActiveNetwork(Context context) {
        Log.v(TAG, "Verificando conexão");

        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static URL buildUrl(String path) {
        Uri builtUri = Uri.parse(BuildConfig.SERVER_URL).buildUpon()
                .appendPath(path)
                .appendQueryParameter(URI_QUERY_API_KEY, BuildConfig.API_KEY).build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Configurando requisição para URL " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();

            return hasInput ? scanner.next() : null;
        } finally {
            urlConnection.disconnect();
        }
    }

}
