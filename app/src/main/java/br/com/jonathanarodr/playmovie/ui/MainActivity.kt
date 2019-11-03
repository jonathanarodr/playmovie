package br.com.jonathanarodr.playmovie.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.jonathanarodr.playmovie.R.id
import br.com.jonathanarodr.playmovie.R.layout
import br.com.jonathanarodr.playmovie.model.Movie
import br.com.jonathanarodr.playmovie.ui.DetailActivity.STATE_FAVORITE
import br.com.jonathanarodr.playmovie.util.NetworkUtils.hasNetworkConnection
import br.com.jonathanarodr.playmovie.util.findViewByLazy
import br.com.jonathanarodr.playmovie.viewmodel.MovieViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : BaseActivity(), MovieAdapterOnClickHandler {

    companion object {
        private const val STATE_BUNDLE_KEY = "state_bundle"
        private const val STATE_MOVIE_KEY = "state_movies"
    }

    @Inject
    private lateinit var movieViewModel: MovieViewModel
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter(this) }
    private val loadingIndicator: ProgressBar by findViewByLazy(id.pb_loading_indicator)
    private val messageError: LinearLayout by findViewByLazy(id.ll_message_error)
    private val listMovie: RecyclerView by findViewByLazy(id.rv_list_movie)
    private val tryAgain: Button by findViewByLazy(id.bt_try_again)
    private val navigation: BottomNavigationView by findViewByLazy(id.bottom_navigation)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        bindListeners()
        buildAdapter()
        buildProviders()
        loadMovies(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(STATE_BUNDLE_KEY, listMovie.layoutManager?.onSaveInstanceState())
        outState.putParcelableArrayList(STATE_MOVIE_KEY, ArrayList(movieAdapter.movies))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            listMovie.layoutManager?.onRestoreInstanceState(
                savedInstanceState.getParcelable(STATE_BUNDLE_KEY)
            )
        }

        movieAdapter.movies = savedInstanceState.getParcelableArrayList(STATE_MOVIE_KEY) ?: emptyList()
    }

    override fun onClick(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Intent.EXTRA_INTENT, movie)

        if (navigation.selectedItemId == id.action_favorite_movie) {
            intent.putExtra(STATE_FAVORITE, movie.id)
        }

        startActivity(intent)
    }

    private fun bindListeners() {
        tryAgain.setOnClickListener {
            when (navigation.selectedItemId) {
                id.action_popular_movie -> searchPopularMovie()
                id.action_top_rate_movie -> searchTopRatedMovie()
                id.action_favorite_movie -> searchFavoriteMovie()
            }
        }

        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                id.action_popular_movie -> {
                    searchPopularMovie()
                    true
                }
                id.action_top_rate_movie -> {
                    searchTopRatedMovie()
                    true
                }
                id.action_favorite_movie -> {
                    searchFavoriteMovie()
                    true
                }
                else -> false
            }
        }
    }

    private fun buildAdapter() {
        listMovie.layoutManager = GridLayoutManager(this,
            3, GridLayoutManager.VERTICAL,false
        )

        listMovie.setHasFixedSize(true)
        listMovie.adapter = movieAdapter
    }

    private fun buildProviders() {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }

    private fun loadMovies(savedInstanceState: Bundle?) {
        if (navigation.selectedItemId != id.action_favorite_movie
            && savedInstanceState == null
            || !savedInstanceState!!.containsKey(STATE_BUNDLE_KEY)
        ) {
            searchPopularMovie()
        }
    }

    private fun showErrorMessageView() {
        loadingIndicator.visibility = View.INVISIBLE
        listMovie.visibility = View.INVISIBLE
        messageError.visibility = View.VISIBLE
    }

    private fun showLoadIndicator() {
        listMovie.visibility = View.INVISIBLE
        messageError.visibility = View.INVISIBLE
        loadingIndicator.visibility = View.VISIBLE
    }

    private fun showListMovieView(movies: List<Movie>) {
        loadingIndicator.visibility = View.INVISIBLE
        messageError.visibility = View.INVISIBLE
        listMovie.visibility = View.VISIBLE
        movieAdapter.movies = movies
    }

    private fun searchPopularMovie() {
        showLoadIndicator()

        if (!hasNetworkConnection(this)) {
            showErrorMessageView()
            return
        }

        movieViewModel.getPopularMovies().observe(this, Observer<List<Movie>> { movies ->
            showListMovieView(movies)
        })
    }

    private fun searchTopRatedMovie() {
        showLoadIndicator()

        if (!hasNetworkConnection(this)) {
            showErrorMessageView()
            return
        }
        movieViewModel.getTopRatedMovies().observe(this, Observer<List<Movie>> { movies ->
            showListMovieView(movies)
        })
    }

    private fun searchFavoriteMovie() {
        showLoadIndicator()

        movieViewModel.getFavoriteMovies().observe(this, Observer<List<Movie>> { movies ->
                showListMovieView(movies)
        })
    }
}