package br.com.jonathanarodr.playmovie.feature.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.jonathanarodr.playmovie.databinding.ActivityDetailBinding
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.ui.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    private val movie: Movie by lazy { getExtra() }

    companion object {
        private const val ARG_MOVIE = "ARG_MOVIE"

        operator fun invoke(context: Context, movie: Movie): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                this.putExtra(ARG_MOVIE, movie)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        setupObservables()
    }

    private fun getExtra(): Movie {
        return requireNotNull(intent.extras?.getParcelable(ARG_MOVIE))
    }

    private fun setupViews() {
        Timber.e(movie.toString())
    }

    private fun setupObservables() {

    }
}