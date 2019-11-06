package br.com.jonathanarodr.playmovie.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent.Builder
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.jonathanarodr.playmovie.R
import br.com.jonathanarodr.playmovie.model.Movie
import br.com.jonathanarodr.playmovie.model.MovieReview
import br.com.jonathanarodr.playmovie.model.MovieVideo
import br.com.jonathanarodr.playmovie.util.AppExecutorsUtils
import br.com.jonathanarodr.playmovie.util.findViewByLazy
import br.com.jonathanarodr.playmovie.viewmodel.MovieViewModel
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private var movieId = DEFAULT_MOVIE_ID
    private lateinit var movie: Movie
    private lateinit var movieViewModel: MovieViewModel
    private var urlYouTube: String? = null
    private val title: TextView by findViewByLazy(R.id.title_detail)
    private val overview: TextView by findViewByLazy(R.id.overview_detail)
    private val release: TextView by findViewByLazy(R.id.release_detail)
    private val average: TextView by findViewByLazy(R.id.average_detail)
    private val poster: ImageView by findViewByLazy(R.id.poster_detail)
    private val loadingIndicator: ProgressBar by findViewByLazy(R.id.pb_loading_review)
    private val listReview: RecyclerView by findViewByLazy(R.id.rv_list_review)
    private val addFavorite: FloatingActionButton by findViewByLazy(R.id.add_favorite)
    private val movieReviewAdapter: MovieReviewAdapter by lazy { MovieReviewAdapter() }

    companion object {
        private const val DEFAULT_MOVIE_ID = -1
        private const val DATE_FORMAT = "yyyy"
        const val STATE_FAVORITE = "state_favorite"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setExtra()
        bindListeners()
        buildAdapter()
        buildProviders()
        searchDetailsMovie()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_FAVORITE, movieId)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            movieId = savedInstanceState.getInt(STATE_FAVORITE)
            updateFavoriteButton()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun isFavoriteMovie(): Boolean {
        return movieId == movie.id
    }

    private fun updateFavoriteButton() {
        if (isFavoriteMovie()) {
            addFavorite.setImageResource(R.drawable.ic_favorite_black_24dp)
        } else {
            addFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp)
        }
    }

    private fun playTrailer() {
        urlYouTube?.let {
            Builder()
                .setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .build()
                .launchUrl(this, Uri.parse(urlYouTube))
        }
    }

    private fun setExtra() {
        if (intent == null) {
            finish()
            return
        }

        if (intent.hasExtra(Intent.EXTRA_INTENT)) {
            movie = intent.getParcelableExtra(Intent.EXTRA_INTENT)
            title.text = movie.title
            overview.text = movie.overview
            release.text = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(movie.releaseDate)
            average.text = movie.average.toString()

            setTitle(movie.title)

            Glide.with(this)
                .load(movie.getPosterHight())
                .centerCrop()
                .placeholder(R.drawable.backgroud_grey)
                .into(poster)
        }

        if (intent.hasExtra(STATE_FAVORITE)) {
            movieId = intent.getIntExtra(STATE_FAVORITE, DEFAULT_MOVIE_ID)
            updateFavoriteButton()
        }
    }

    private fun bindListeners() {
        poster.setOnClickListener {
            playTrailer()
        }

        addFavorite.setOnClickListener {
            if (isFavoriteMovie()) {
                deleteFavoriteMovie()
            } else {
                insertFavoriteMovie()
            }
        }
    }

    private fun buildAdapter() {
        listReview.layoutManager = LinearLayoutManager(this)
        listReview.setHasFixedSize(true)
        listReview.adapter = movieReviewAdapter
    }

    private fun buildProviders() {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }

    private fun searchDetailsMovie() {
        movieViewModel.getVideos(movie.id).observe(this, Observer<List<MovieVideo>> { videos ->
            urlYouTube = videos.firstOrNull()?.getUrlVideo()
        })

        movieViewModel.getReviews(movie.id).observe(this, Observer<List<MovieReview>> { reviews ->
            movieReviewAdapter.reviews = reviews
            loadingIndicator.visibility = View.INVISIBLE
        })
    }

    private fun insertFavoriteMovie() {
        AppExecutorsUtils.getInstance().diskIO().execute {
            movieViewModel.insertFavoriteMovie(movie)
            movieId = movie.id
            updateFavoriteButton()
        }
    }

    private fun deleteFavoriteMovie() {
        AppExecutorsUtils.getInstance().diskIO().execute {
            movieViewModel.deleteFavoriteMovie(movie)
            movieId = DEFAULT_MOVIE_ID
            updateFavoriteButton()
        }
    }
}