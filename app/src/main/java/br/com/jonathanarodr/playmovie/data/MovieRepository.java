package br.com.jonathanarodr.playmovie.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import br.com.jonathanarodr.playmovie.BuildConfig;
import br.com.jonathanarodr.playmovie.api.ApiService;
import br.com.jonathanarodr.playmovie.api.MovieService;
import br.com.jonathanarodr.playmovie.model.Movie;
import br.com.jonathanarodr.playmovie.model.MovieApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static final String TAG = MovieRepository.class.getSimpleName();
    private MovieService movieService = ApiService.build().create(MovieService.class);

    public LiveData<List<Movie>> searchPopularMovie() {
        final MutableLiveData<List<Movie>> data = new MutableLiveData<>();

        movieService.searchPopularMovie(BuildConfig.API_KEY).enqueue(new Callback<MovieApi>() {
            @Override
            public void onResponse(Call<MovieApi> call, Response<MovieApi> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getMovies());
                }
            }

            @Override
            public void onFailure(Call<MovieApi> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<List<Movie>> searchTopRatedMovie() {
        final MutableLiveData<List<Movie>> data = new MutableLiveData<>();

        movieService.searchTopRatedMovie(BuildConfig.API_KEY).enqueue(new Callback<MovieApi>() {
            @Override
            public void onResponse(Call<MovieApi> call, Response<MovieApi> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getMovies());
                }
            }

            @Override
            public void onFailure(Call<MovieApi> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                data.setValue(null);
            }
        });

        return data;
    }

}
