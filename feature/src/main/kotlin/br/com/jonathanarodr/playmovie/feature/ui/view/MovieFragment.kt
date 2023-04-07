package br.com.jonathanarodr.playmovie.feature.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.jonathanarodr.playmovie.common.states.UiState
import br.com.jonathanarodr.playmovie.common.uikit.ext.setContent
import br.com.jonathanarodr.playmovie.common.uikit.token.Color
import br.com.jonathanarodr.playmovie.common.uikit.token.Spacing
import br.com.jonathanarodr.playmovie.feature.ui.viewmodel.MovieViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModel()
    private val args: MovieFragmentArgs by navArgs()

    @ExperimentalMaterial3Api
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = setContent {
        val result = viewModel.fetchMovies.observeAsState().value

        val isLoading = result is UiState.Loading
        val movies = (result as? UiState.Success)?.data ?: emptyList()

        MovieScreen(
            modifier = Modifier
                .background(Color.neutralBack),
            onRefresh = ::onRefresh,
            onClick = {},
            isRefreshing = isLoading,
            movies = movies,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMovies(args.movieType)
    }

    private fun onRefresh() {
        viewModel.fetchMovies(args.movieType)
    }
}
