package br.com.jonathanarodr.playmovie.feature.ui.view

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.jonathanarodr.playmovie.common.uikit.theme.AppTheme
import br.com.jonathanarodr.playmovie.common.uikit.token.Spacing
import br.com.jonathanarodr.playmovie.feature.R
import br.com.jonathanarodr.playmovie.feature.ui.model.MovieUiModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.util.Collections.emptyList

@ExperimentalMaterial3Api
@Composable
fun MovieScreen(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit,
    onClick: (MovieUiModel) -> Unit,
    movies: List<MovieUiModel>,
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        SwipeRefresh(
            modifier = Modifier.padding(innerPadding),
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
            onRefresh = { onRefresh() }
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Adaptive(minSize = dimensionResource(id = R.dimen.card_movie_width)),
                horizontalArrangement = Arrangement.spacedBy(Spacing.lg),
                verticalArrangement = Arrangement.spacedBy(Spacing.lg),
                contentPadding = PaddingValues(start = Spacing.lg, end = Spacing.lg, bottom = 72.dp),
            ) {
                upcomingMovie(
                    movies = movies,
                    onClick = onClick,
                )
                nowPlayingMovie(
                    movies = movies,
                    onClick = onClick,
                )
                popularMovie(
                    movies = movies,
                    onClick = onClick,
                )
            }
        }
    }
}

private fun LazyGridScope.sectionLayout(
    @StringRes title: Int,
) {
    item(
        span = {
            GridItemSpan(maxLineSpan)
        }
    ) {
        SectionMovieLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Spacing.sm),
            title = title,
        )
    }
}

@ExperimentalMaterial3Api
private fun LazyGridScope.upcomingMovie(
    movies: List<MovieUiModel>,
    onClick: (MovieUiModel) -> Unit,
) {
    sectionLayout(
        title = R.string.section_movie_upcoming,
    )
    item(
        span = {
            GridItemSpan(maxLineSpan)
        }
    ) {
        MovieCarouselLayout(
            modifier = Modifier.fillMaxWidth(),
            movies = movies,
        )
    }
}

@ExperimentalMaterial3Api
private fun LazyGridScope.nowPlayingMovie(
    movies: List<MovieUiModel>,
    onClick: (MovieUiModel) -> Unit,
) {
    sectionLayout(
        title = R.string.section_movie_now_playing,
    )
    item(
        span = {
            GridItemSpan(maxLineSpan)
        }
    ) {
        MovieCarouselLayout(
            modifier = Modifier.fillMaxWidth(),
            movies = movies,
        )
    }
}

@ExperimentalMaterial3Api
private fun LazyGridScope.popularMovie(
    movies: List<MovieUiModel>,
    onClick: (MovieUiModel) -> Unit,
) {
    sectionLayout(
        title = R.string.section_movie_popular,
    )
    items(movies) { uiModel ->
        MovieCardLayout(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.card_movie_width))
                .clickable { onClick(uiModel) },
            uiModel = uiModel
        )
    }
}

@Preview(showBackground = true)
@ExperimentalMaterial3Api
@Composable
fun MovieScreenPreview() {
    AppTheme {
        MovieScreen(
            onRefresh = {},
            onClick = {},
            movies = emptyList(),
        )
    }
}
