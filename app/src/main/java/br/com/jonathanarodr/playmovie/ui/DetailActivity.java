package br.com.jonathanarodr.playmovie.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import br.com.jonathanarodr.playmovie.R;
import br.com.jonathanarodr.playmovie.model.Movie;
import br.com.jonathanarodr.playmovie.model.MovieReview;
import br.com.jonathanarodr.playmovie.model.MovieVideo;
import br.com.jonathanarodr.playmovie.util.AppExecutorsUtil;
import br.com.jonathanarodr.playmovie.viewmodel.MovieViewModel;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();
    private static final int DEFAULT_MOVIE_ID = -1;
    private static final String DATE_FORMAT = "yyyy";
    public static final String STATE_FAVORITE = "state_favorite";

    private MovieViewModel mMovieViewModel;
    private String urlYouTube;
    private int mMovieId ;
    private Movie mMovie;
    private TextView mTitle;
    private TextView mOverview;
    private TextView mRelease;
    private TextView mAverage;
    private ImageView mPoster;
    private ProgressBar mLoadingIndicator;
    private RecyclerView mListReview;
    private MovieReviewAdapter mMovieReviewAdapter;
    private FloatingActionButton mAddFavorite;

    private View.OnClickListener playTrailerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            playTrailer();
        }
    };

    private View.OnClickListener favoriteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mMovieId != mMovie.getId())
                insertFavoriteMovie();
            else {
                deleteFavoriteMovie();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bindView();
        setExtra();
        buildAdapter();
        buildProviders();
        searchDetailsVideo();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_FAVORITE, mMovieId);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            mMovieId = savedInstanceState.getInt(STATE_FAVORITE);
            atualizaBotaoFavorito();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void bindView() {
        mTitle = findViewById(R.id.title_detail);
        mOverview = findViewById(R.id.overview_detail);
        mRelease = findViewById(R.id.release_detail);
        mAverage = findViewById(R.id.average_detail);
        mPoster = findViewById(R.id.poster_detail);
        mLoadingIndicator = findViewById(R.id.pb_loading_review);
        mListReview = findViewById(R.id.rv_list_review);
        mAddFavorite = findViewById(R.id.add_favorite);

        mPoster.setOnClickListener(playTrailerClickListener);
        mAddFavorite.setOnClickListener(favoriteClickListener);
    }

    private void setExtra() {
        if (getIntent() == null) {
            return;
        }

        if (getIntent().hasExtra(Intent.EXTRA_INTENT)) {
            mMovie = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
            mTitle.setText(mMovie.getTitle());
            mOverview.setText(mMovie.getOverview());
            mRelease.setText(new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(mMovie.getReleaseDate()));
            mAverage.setText(mMovie.getAverage().toString());
            Picasso.get().load(mMovie.getPosterHight()).placeholder(R.drawable.backgroud_grey).into(mPoster);
            setTitle(mMovie.getTitle());
        }

        if (getIntent().hasExtra(STATE_FAVORITE)) {
            mMovieId = getIntent().getIntExtra(STATE_FAVORITE, DEFAULT_MOVIE_ID);
            atualizaBotaoFavorito();
        }
    }

    private void buildAdapter() {
        mListReview.setLayoutManager(new LinearLayoutManager(this));
        mListReview.setHasFixedSize(true);
        mMovieReviewAdapter = new MovieReviewAdapter();
        mListReview.setAdapter(mMovieReviewAdapter);
    }

    private void buildProviders() {
        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
    }

    private void searchDetailsVideo() {
        Log.d(TAG, "Consutando detalhes do video");
        mMovieViewModel.getVideos(mMovie.getId()).observe(this, new Observer<List<MovieVideo>>() {
            @Override
            public void onChanged(@Nullable List<MovieVideo> movieVideos) {
                Log.d(TAG, "Consutando detalhes do video via cache");
                urlYouTube = movieVideos.get(0).getKey();
            }
        });

        mMovieViewModel.getReviews(mMovie.getId()).observe(this, new Observer<List<MovieReview>>() {
            @Override
            public void onChanged(@Nullable List<MovieReview> movieReviews) {
                mMovieReviewAdapter.setReviews(movieReviews);
                mLoadingIndicator.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void playTrailer() {
        if (urlYouTube == null) {
            return;
        }

        CustomTabsIntent.Builder customTabs = new CustomTabsIntent.Builder();
        customTabs.setToolbarColor(getResources().getColor(R.color.colorPrimary));
        customTabs.build().launchUrl(this, Uri.parse(urlYouTube));
    }

    private void insertFavoriteMovie() {
        AppExecutorsUtil.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mMovieViewModel.insertFavoriteMovie(mMovie);
                mMovieId = mMovie.getId();
                atualizaBotaoFavorito();
            }
        });
    }

    private void deleteFavoriteMovie() {
        AppExecutorsUtil.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mMovieViewModel.deleteFavoriteMovie(mMovie);
                mMovieId = DEFAULT_MOVIE_ID;
                atualizaBotaoFavorito();
            }
        });
    }

    private void atualizaBotaoFavorito() {
        if (mMovie.getId() == mMovieId) {
            mAddFavorite.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            mAddFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
    }

}
