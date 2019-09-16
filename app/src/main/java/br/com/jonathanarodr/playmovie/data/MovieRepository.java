package br.com.jonathanarodr.playmovie.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.jonathanarodr.playmovie.BuildConfig;
import br.com.jonathanarodr.playmovie.api.MovieService;
import br.com.jonathanarodr.playmovie.model.Movie;
import br.com.jonathanarodr.playmovie.model.MovieReview;
import br.com.jonathanarodr.playmovie.model.MovieVideo;
import br.com.jonathanarodr.playmovie.model.ResultApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static final String TAG = MovieRepository.class.getSimpleName();
    private final MovieService mMovieService;
    private final MovieDao mMovieDao;

    public MovieRepository(MovieService movieService, MovieDao movieDao) {
        mMovieService = movieService;
        mMovieDao = movieDao;
    }

    public LiveData<List<Movie>> searchPopularMovies() {
        final MutableLiveData<List<Movie>> data = new MutableLiveData<>();

        mMovieService.searchPopularMovies(BuildConfig.API_KEY).enqueue(new Callback<ResultApi<Movie>>() {
            @Override
            public void onResponse(Call<ResultApi<Movie>> call, Response<ResultApi<Movie>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ResultApi<Movie>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<List<Movie>> searchTopRatedMovies() {
        final MutableLiveData<List<Movie>> data = new MutableLiveData<>();

        mMovieService.searchTopRatedMovies(BuildConfig.API_KEY).enqueue(new Callback<ResultApi<Movie>>() {
            @Override
            public void onResponse(Call<ResultApi<Movie>> call, Response<ResultApi<Movie>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ResultApi<Movie>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<List<MovieVideo>> searchVideos(int movieId) {
        final MutableLiveData<List<MovieVideo>> data = new MutableLiveData<>();

        mMovieService.searchVideos(movieId, BuildConfig.API_KEY).enqueue(new Callback<ResultApi<MovieVideo>>() {
            @Override
            public void onResponse(Call<ResultApi<MovieVideo>> call, Response<ResultApi<MovieVideo>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ResultApi<MovieVideo>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<List<MovieReview>> searchReviews(int movieId) {
        final MutableLiveData<List<MovieReview>> data = new MutableLiveData<>();

        mMovieService.searchReviews(movieId, BuildConfig.API_KEY).enqueue(new Callback<ResultApi<MovieReview>>() {
            @Override
            public void onResponse(Call<ResultApi<MovieReview>> call, Response<ResultApi<MovieReview>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ResultApi<MovieReview>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<List<Movie>> searchFavoriteMovies() {
        return mMovieDao.listMovies();
    }

    public void insertFavoriteMovie(Movie movie) {
        mMovieDao.insertMovie(movie);
    }

    public void deleteFavoriteMovie(Movie movie) {
        mMovieDao.deleteMovie(movie);
    }

}
