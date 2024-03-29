package br.com.jonathanarodr.playmovie.feature.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import br.com.jonathanarodr.playmovie.common.states.observeOnError
import br.com.jonathanarodr.playmovie.common.states.observeOnSuccess
import br.com.jonathanarodr.playmovie.common.utils.ImageLoaderUtils
import br.com.jonathanarodr.playmovie.common.utils.ImageLoaderUtils.IMAGE_SIZE_DEFAULT
import br.com.jonathanarodr.playmovie.common.utils.ImageLoaderUtils.IMAGE_SIZE_HIGH
import br.com.jonathanarodr.playmovie.common.utils.ImageSize
import br.com.jonathanarodr.playmovie.common.utils.format
import br.com.jonathanarodr.playmovie.common.utils.putSafeArgs
import br.com.jonathanarodr.playmovie.common.utils.safeArgs
import br.com.jonathanarodr.playmovie.feature.R
import br.com.jonathanarodr.playmovie.feature.databinding.ActivityDetailBinding
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.ui.viewmodel.DetailViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    private val movie: Movie by safeArgs()

    companion object {
        operator fun invoke(context: Context, movie: Movie): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                this.putSafeArgs(movie)
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

        viewModel.insertedMovie
            .observeOnSuccess(this, ::onInsertSuccess)
            .observeOnError(this, ::onError)

        viewModel.removedMovie
            .observeOnSuccess(this, ::onRemoveSuccess)
            .observeOnError(this, ::onError)
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
