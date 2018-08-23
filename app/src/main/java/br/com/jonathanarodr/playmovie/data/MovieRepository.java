package br.com.jonathanarodr.playmovie.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import br.com.jonathanarodr.playmovie.BuildConfig;
import br.com.jonathanarodr.playmovie.api.MovieService;
import br.com.jonathanarodr.playmovie.model.Movie;
import br.com.jonathanarodr.playmovie.model.MovieApi;
import br.com.jonathanarodr.playmovie.model.MovieReview;
import br.com.jonathanarodr.playmovie.model.MovieReviewApi;
import br.com.jonathanarodr.playmovie.model.MovieVideo;
import br.com.jonathanarodr.playmovie.model.MovieVideoApi;
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

        mMovieService.searchPopularMovies(BuildConfig.API_KEY).enqueue(new Callback<MovieApi>() {
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

    public LiveData<List<Movie>> searchTopRatedMovies() {
        final MutableLiveData<List<Movie>> data = new MutableLiveData<>();

        mMovieService.searchTopRatedMovies(BuildConfig.API_KEY).enqueue(new Callback<MovieApi>() {
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

    public LiveData<List<MovieVideo>> searchVideos(int movieId) {
        final MutableLiveData<List<MovieVideo>> data = new MutableLiveData<>();

        mMovieService.searchVideos(movieId, BuildConfig.API_KEY).enqueue(new Callback<MovieVideoApi>() {
            @Override
            public void onResponse(Call<MovieVideoApi> call, Response<MovieVideoApi> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getVideos());
                }
            }

            @Override
            public void onFailure(Call<MovieVideoApi> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<List<MovieReview>> searchReviews(int movieId) {
        final MutableLiveData<List<MovieReview>> data = new MutableLiveData<>();

        mMovieService.searchReviews(movieId, BuildConfig.API_KEY).enqueue(new Callback<MovieReviewApi>() {
            @Override
            public void onResponse(Call<MovieReviewApi> call, Response<MovieReviewApi> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getReviews());
                }
            }

            @Override
            public void onFailure(Call<MovieReviewApi> call, Throwable t) {
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
