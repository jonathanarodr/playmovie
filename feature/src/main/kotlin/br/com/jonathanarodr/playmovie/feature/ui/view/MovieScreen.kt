package br.com.jonathanarodr.playmovie.feature.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.jonathanarodr.playmovie.common.states.UiState
import br.com.jonathanarodr.playmovie.common.uikit.theme.AppTheme
import br.com.jonathanarodr.playmovie.feature.R
import br.com.jonathanarodr.playmovie.feature.ui.model.MovieUiModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.util.Collections.emptyList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit,
    state: UiState<List<MovieUiModel>>,
) {

    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
            onRefresh = { onRefresh() }
        ) {
            LazyColumn(
                modifier = Modifier.padding(innerPadding)
            ) {
                //TODO("updcoming list")
                //TODO("playing now list")
                item {
                    MovieGridLayout(
                        titleRes = R.string.section_movie_popular,
                        movies = emptyList(),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieScreenPreview() {
    AppTheme {
        MovieScreen(
            onRefresh = {},
            state = UiState.EmptyState,
        )
    }
}
