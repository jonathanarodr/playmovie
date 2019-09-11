package br.com.jonathanarodr.playmovie.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import java.util.List;

import br.com.jonathanarodr.playmovie.api.ApiService;
import br.com.jonathanarodr.playmovie.api.MovieService;
import br.com.jonathanarodr.playmovie.data.AppDatabase;
import br.com.jonathanarodr.playmovie.data.MovieDao;
import br.com.jonathanarodr.playmovie.data.MovieRepository;
import br.com.jonathanarodr.playmovie.model.Movie;
import br.com.jonathanarodr.playmovie.model.MovieReview;
import br.com.jonathanarodr.playmovie.model.MovieVideo;

public class MovieViewModel extends AndroidViewModel {

    private static final String TAG = MovieViewModel.class.getSimpleName();
    private MovieRepository mMovieRepository;

    public MovieViewModel(@NonNull Application application) {
        super(application);

        MovieService service = ApiService.getInstance().create(MovieService.class);
        MovieDao dao = AppDatabase.getInstance(this.getApplication()).movieDao();

        mMovieRepository = new MovieRepository(service, dao);
    }

    public LiveData<List<Movie>> getPopularMovies() {
        return this.mMovieRepository.searchPopularMovies();
    }

    public LiveData<List<Movie>> getTopRatedMovies() {
        return this.mMovieRepository.searchTopRatedMovies();
    }

    public LiveData<List<MovieVideo>> getVideos(int movieId) {
        return this.mMovieRepository.searchVideos(movieId);
    }

    public LiveData<List<MovieReview>> getReviews(int movieId) {
        return this.mMovieRepository.searchReviews(movieId);
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        return this.mMovieRepository.searchFavoriteMovies();
    }

    public void insertFavoriteMovie(Movie movie) {
        mMovieRepository.insertFavoriteMovie(movie);
    }

    public void deleteFavoriteMovie(Movie movie) {
        mMovieRepository.deleteFavoriteMovie(movie);
    }

}