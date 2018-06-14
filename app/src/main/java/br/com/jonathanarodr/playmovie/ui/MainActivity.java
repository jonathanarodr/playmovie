package br.com.jonathanarodr.playmovie.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import br.com.jonathanarodr.playmovie.R;
import br.com.jonathanarodr.playmovie.model.Movie;
import br.com.jonathanarodr.playmovie.utils.JsonUtils;
import br.com.jonathanarodr.playmovie.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity implements MovieAdapterOnClickHandler {

    private static final String STATE_BUNDLE_KEY = "state_bundle";
    private static final String STATE_MOVIE_KEY = "state_movies";
    private static final String URI_POPULAR_MOVIE = "popular";
    private static final String URI_TOP_RATED_MOVIE = "top_rated";

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

        if(savedInstanceState == null || !savedInstanceState.containsKey(STATE_BUNDLE_KEY)) {
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
        startActivity(intent);
    }

    private void showErrorMessageView() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mListMovie.setVisibility(View.INVISIBLE);
        mMessageError.setVisibility(View.VISIBLE);
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

    private void searchPopularMovie() {
        new MovieTask().execute(URI_POPULAR_MOVIE);
    }

    private void searchTopRatedMovie() {
        new MovieTask().execute(URI_TOP_RATED_MOVIE);
    }

    private void showListMovieView() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mMessageError.setVisibility(View.INVISIBLE);
        mListMovie.setVisibility(View.VISIBLE);
    }

    public class MovieTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mListMovie.setVisibility(View.INVISIBLE);
            mMessageError.setVisibility(View.INVISIBLE);
            mLoadingIndicator.setVisibility(View.VISIBLE);
            mMovieAdapter.setMovies(null);
        }

        @Override
        protected String doInBackground(String... paths) {
            if (paths.length == 0) {
                return null;
            }

            try {
                if (!NetworkUtils.isActiveNetwork(MainActivity.this)) {
                    cancel(true);
                    return null;
                }

                URL url = NetworkUtils.buildUrl(paths[0]);
                return NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            if (jsonResponse == null) {
                showErrorMessageView();
            } else {
                showListMovieView();
                mMovieAdapter.setMovies(JsonUtils.fromJson(jsonResponse));
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            showErrorMessageView();
        }
    }

}
