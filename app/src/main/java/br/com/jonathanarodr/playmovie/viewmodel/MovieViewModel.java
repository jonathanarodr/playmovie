package br.com.jonathanarodr.playmovie.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Singleton;

import br.com.jonathanarodr.playmovie.data.MovieRepository;
import br.com.jonathanarodr.playmovie.model.Movie;

@Singleton
public class MovieViewModel extends ViewModel {

    private static final String TAG = MovieViewModel.class.getSimpleName();

    private MovieRepository repository;

    public MovieViewModel() {
        this.repository = new MovieRepository();
    }

    public LiveData<List<Movie>> getPopularMovies() {
        return this.repository.searchPopularMovie();
    }

    public LiveData<List<Movie>> getTopRatedMovies() {
        return this.repository.searchTopRatedMovie();
    }

}