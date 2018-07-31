package br.com.jonathanarodr.playmovie.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import br.com.jonathanarodr.playmovie.R;
import br.com.jonathanarodr.playmovie.model.Movie;
import br.com.jonathanarodr.playmovie.util.NetworkUtils;
import br.com.jonathanarodr.playmovie.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity implements MovieAdapterOnClickHandler {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    private MovieViewModel mMovieViewModel;

    private ProgressBar mLoadingIndicator;
    private LinearLayout mMessageError;
    private RecyclerView mListMovie;
    private MovieAdapter mMovieAdapter;
    private Button mTryAgain;
    private BottomNavigationView mNavigation;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_popular_movie:
                    searchPopularMovie();
                    return true;
                case R.id.action_top_rate_movie:
                    searchTopRatedMovie();
                    return true;
                default:
                    return false;
            }
        }
    };

    private View.OnClickListener tryAgainClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (mNavigation.getSelectedItemId()) {
                case R.id.action_popular_movie:
                    searchPopularMovie();
                    break;
                case R.id.action_top_rate_movie:
                    searchTopRatedMovie();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
        buildAdapter();
        buildProviders();
        searchPopularMovie();
    }

    @Override
    public void onClick(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, movie);
        startActivity(intent);
    }

    private void bindView() {
        this.mLoadingIndicator = findViewById(R.id.pb_loading_indicator);
        this.mMessageError = findViewById(R.id.ll_message_error);
        this.mListMovie = findViewById(R.id.rv_list_movie);
        this.mTryAgain = findViewById(R.id.bt_try_again);
        this.mNavigation = findViewById(R.id.bottom_navigation);

        this.mTryAgain.setOnClickListener(tryAgainClickListener);
        this.mNavigation.setOnNavigationItemSelectedListener(navigationListener);
    }

    private void buildAdapter() {
        mListMovie.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        mListMovie.setHasFixedSize(true);
        mMovieAdapter = new MovieAdapter(this);
        mListMovie.setAdapter(mMovieAdapter);
    }

    private void buildProviders() {
         mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
    }

    private void searchPopularMovie() {
        showLoadIndicator();

        if (!NetworkUtils.isActiveNetwork(this)) {
            showErrorMessageView();
            return;
        }

        mMovieViewModel.getPopularMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                showListMovieView(movies);
            }
        });
    }

    private void searchTopRatedMovie() {
        showLoadIndicator();

        if (!NetworkUtils.isActiveNetwork(this)) {
            showErrorMessageView();
            return;
        }

        mMovieViewModel.getTopRatedMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                showListMovieView(movies);
            }
        });
    }

    private void showErrorMessageView() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mListMovie.setVisibility(View.INVISIBLE);
        mMessageError.setVisibility(View.VISIBLE);
    }

    private void showLoadIndicator() {
        mListMovie.setVisibility(View.INVISIBLE);
        mMessageError.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
        mMovieAdapter.setMovies(null);
    }

    private void showListMovieView(List<Movie> movies) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mMessageError.setVisibility(View.INVISIBLE);
        mListMovie.setVisibility(View.VISIBLE);
        mMovieAdapter.setMovies(movies);
    }

}
