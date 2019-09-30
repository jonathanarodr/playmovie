package br.com.jonathanarodr.playmovie.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.jonathanarodr.playmovie.R;
import br.com.jonathanarodr.playmovie.model.Movie;
import br.com.jonathanarodr.playmovie.util.NetworkUtils;
import br.com.jonathanarodr.playmovie.viewmodel.MovieViewModel;

import static br.com.jonathanarodr.playmovie.ui.DetailActivity.STATE_FAVORITE;

public class MainActivity extends BaseActivity implements MovieAdapterOnClickHandler {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String STATE_BUNDLE_KEY = "state_bundle";
    private static final String STATE_MOVIE_KEY = "state_movies";

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
                case R.id.action_favorite_movie:
                    searchFavoriteMovie();
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
                case R.id.action_favorite_movie:
                    searchFavoriteMovie();
                    break;
                default:
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

        if(mNavigation.getSelectedItemId() != R.id.action_favorite_movie
                && savedInstanceState == null
                || !savedInstanceState.containsKey(STATE_BUNDLE_KEY)) {
            searchPopularMovie();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATE_BUNDLE_KEY, mListMovie.getLayoutManager().onSaveInstanceState());
        outState.putParcelableArrayList(STATE_MOVIE_KEY, new ArrayList<>(mMovieAdapter.getMovies()));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            mListMovie.getLayoutManager().onRestoreInstanceState(savedInstanceState.getParcelable(STATE_BUNDLE_KEY));
            mMovieAdapter.setMovies(savedInstanceState.<Movie>getParcelableArrayList(STATE_MOVIE_KEY));
        }

    }

    @Override
    public void onClick(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, movie);

        if (mNavigation.getSelectedItemId() == R.id.action_favorite_movie) {
            intent.putExtra(STATE_FAVORITE, movie.getId());
        }

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

        if (!NetworkUtils.hasNetworkConnection(this)) {
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

        if (!NetworkUtils.hasNetworkConnection(this)) {
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

    private void searchFavoriteMovie() {
        showLoadIndicator();

        mMovieViewModel.getFavoriteMovies().observe(this, new Observer<List<Movie>>() {
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
