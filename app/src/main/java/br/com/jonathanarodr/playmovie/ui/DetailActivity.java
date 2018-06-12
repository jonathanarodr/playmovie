package br.com.jonathanarodr.playmovie.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

import br.com.jonathanarodr.playmovie.R;
import br.com.jonathanarodr.playmovie.model.Movie;

public class DetailActivity extends AppCompatActivity {

    private final String DATE_FORMAT = "yyyy";

    private Movie mMovie;
    private TextView mTitle;
    private TextView mOverview;
    private TextView mRelease;
    private TextView mAverage;
    private ImageView mPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bindView();
        setExtra();
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
    }

    private void setExtra() {
        if (getIntent() == null || !getIntent().hasExtra(Intent.EXTRA_INTENT)) {
            return;
        }

        mMovie = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
        mTitle.setText(mMovie.getTitle());
        mOverview.setText(mMovie.getOverview());
        mRelease.setText(new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(mMovie.getReleaseDate()));
        mAverage.setText(mMovie.getAverage().toString());
        Picasso.get().load(mMovie.getPosterHight()).placeholder(R.drawable.backgroud_grey).into(mPoster);
    }

}
