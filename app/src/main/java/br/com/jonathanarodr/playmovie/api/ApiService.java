package br.com.jonathanarodr.playmovie.api;

import br.com.jonathanarodr.playmovie.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class ApiService {

    public static Retrofit build() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
