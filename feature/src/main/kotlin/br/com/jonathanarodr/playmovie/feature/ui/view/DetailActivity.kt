package br.com.jonathanarodr.playmovie.feature.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.com.jonathanarodr.playmovie.common.utils.ImageLoaderUtils
import br.com.jonathanarodr.playmovie.common.utils.ImageLoaderUtils.IMAGE_SIZE_DEFAULT
import br.com.jonathanarodr.playmovie.common.utils.ImageLoaderUtils.IMAGE_SIZE_HIGH
import br.com.jonathanarodr.playmovie.common.utils.ImageSize
import br.com.jonathanarodr.playmovie.common.utils.format
import br.com.jonathanarodr.playmovie.common.utils.putSafeArgs
import br.com.jonathanarodr.playmovie.common.utils.safeArgs
import br.com.jonathanarodr.playmovie.feature.R
import br.com.jonathanarodr.playmovie.feature.databinding.ActivityDetailBinding
import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType
import br.com.jonathanarodr.playmovie.feature.ui.model.DetailUiModel
import br.com.jonathanarodr.playmovie.feature.ui.states.DetailUiEvent
import br.com.jonathanarodr.playmovie.feature.ui.states.DetailUiState
import br.com.jonathanarodr.playmovie.feature.ui.viewmodel.DetailViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel(parameters = { parametersOf(args) })
    private val args: MovieSafeArgs by safeArgs()

    companion object {
        operator fun invoke(
            context: Context,
            movieId: Long,
            movieType: MovieType,
        ): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                this.putSafeArgs(
                    MovieSafeArgs(
                        id = movieId,
                        type = movieType,
                    )
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservables()
    }

    private fun setupObservables() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is DetailUiState.Success -> onSuccess(state.data)
                        is DetailUiState.Loading -> onLoading()
                        is DetailUiState.Error -> onError(state.exception)
                        is DetailUiState.LikedMovie -> onLikedSuccess()
                        is DetailUiState.DislikedMovie -> onDislikedSuccess()
                    }
                }
            }
        }
    }

    private fun onSuccess(uiModel: DetailUiModel) {
        binding.apply {
            detailToolbar.setNavigationOnClickListener { finish() }
            moviePoster.loadImage(uiModel.poster, IMAGE_SIZE_DEFAULT)
            movieBackdrop.loadImage(uiModel.backdrop, IMAGE_SIZE_HIGH)
            movieTitle.text = uiModel.title
            movieRelease.text = uiModel.releaseDate.format()
            movieAverage.text = uiModel.average.toString()
            movieOverviewDescription.text = uiModel.overview
        }
    }

    // TODO create a load feedback
    private fun onLoading() {
        Timber.i("Searching movie details by id #${args.id}")
    }

    private fun onError(cause: Throwable) {
        Timber.e(cause)

        Snackbar.make(
            binding.root,
            R.string.generic_message_ops_try_again,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onLikedSuccess() {
        binding.saveFavoriteMovie.updateView(R.drawable.ic_favorite_on) {
            viewModel.dispatchUiEvent(DetailUiEvent.RemoveFavorite)
        }
    }

    private fun onDislikedSuccess() {
        binding.saveFavoriteMovie.updateView(R.drawable.ic_favorite_off) {
            viewModel.dispatchUiEvent(DetailUiEvent.InsertFavorite)
        }
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
