package br.com.jonathanarodr.playmovie.feature.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import br.com.jonathanarodr.playmovie.R
import br.com.jonathanarodr.playmovie.core.common.UiState
import br.com.jonathanarodr.playmovie.core.utils.ImageLoaderUtils
import br.com.jonathanarodr.playmovie.core.utils.ImageLoaderUtils.IMAGE_SIZE_DEFAULT
import br.com.jonathanarodr.playmovie.core.utils.ImageLoaderUtils.IMAGE_SIZE_HIGH
import br.com.jonathanarodr.playmovie.core.utils.ImageSize
import br.com.jonathanarodr.playmovie.core.utils.format
import br.com.jonathanarodr.playmovie.databinding.ActivityDetailBinding
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.ui.viewmodel.DetailViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
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

    private fun setupViews() {
        binding.apply {
            detailToolbar.setNavigationOnClickListener { finish() }
            moviePoster.loadImage(movie.poster, IMAGE_SIZE_DEFAULT)
            movieBackdrop.loadImage(movie.backdrop, IMAGE_SIZE_HIGH)
            movieTitle.text = movie.title
            movieRelease.text = movie.releaseDate.format()
            movieAverage.text = movie.average.toString()
            movieOverviewDescription.text = movie.overview
        }

        viewModel.verifyFavoriteMovie(movie.id)
    }

    private fun setupObservables() {
        viewModel.isFavorite.observe(this) { result ->
            if (result) {
                onInsertSuccess()
            } else {
                onRemoveSuccess()
            }
        }

        viewModel.insertedMovie.observe(this) {
            when (it) {
                is UiState.Success -> onInsertSuccess()
                is UiState.Error -> onError(it.cause)
                else -> Timber.w("UiState $it not mapped")
            }
        }

        viewModel.removedMovie.observe(this) {
            when (it) {
                is UiState.Success -> onRemoveSuccess()
                is UiState.Error -> onError(it.cause)
                else -> Timber.w("UiState $it not mapped")
            }
        }
    }

    private fun onInsertSuccess() {
        binding.saveFavoriteMovie.updateView(R.drawable.ic_favorite_on) {
            viewModel.removeFavoriteMovie(movie)
        }
    }

    private fun onRemoveSuccess() {
        binding.saveFavoriteMovie.updateView(R.drawable.ic_favorite_off) {
            viewModel.insertFavoriteMovie(movie)
        }
    }

    private fun onError(cause: Throwable) {
        Timber.e(cause)

        Snackbar.make(
            binding.root,
            R.string.generic_message_ops_try_again,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun getExtra(): Movie {
        return requireNotNull(intent.extras?.getParcelable(ARG_MOVIE))
    }

    private fun AppCompatImageView.loadImage(image: String?, size: ImageSize) {
        if (!image.isNullOrEmpty()) {
            ImageLoaderUtils.load(this, image, size)
        }
    }

    private fun FloatingActionButton.updateView(@DrawableRes icon: Int, action: () -> Unit) {
        setImageDrawable(AppCompatResources.getDrawable(context, icon))
        setOnClickListener {
            action.invoke()
        }
    }
}