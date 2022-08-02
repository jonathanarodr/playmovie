package br.com.jonathanarodr.playmovie.feature.ui.view

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.jonathanarodr.playmovie.common.uikit.theme.AppTheme
import br.com.jonathanarodr.playmovie.common.uikit.token.Spacing
import br.com.jonathanarodr.playmovie.feature.R
import br.com.jonathanarodr.playmovie.feature.ui.model.MovieUiModel
import java.util.*

@Composable
fun MovieListLayout(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    movies: List<MovieUiModel>,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = Spacing.lg),
        horizontalArrangement = Arrangement.spacedBy(Spacing.lg),
    ) {
        item {
            SectionMovieLayout(title = titleRes)
        }
        items(movies) { uiModel ->
            MovieCardLayout(
                modifier = Modifier.width(dimensionResource(id = R.dimen.card_movie_width)),
                uiModel = uiModel
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MovieListLayoutPreview() {
    AppTheme {
        MovieListLayout(
            titleRes = R.string.section_movie_popular,
            movies = listOf(
                MovieUiModel(
                    id = 0L,
                    title = "Thor: Love and Thunder",
                    posterUrl = "https://image.tmdb.org/t/p/w185/6OEBp0Gqv6DsOgi8diPUslT2kbA.jpg",
                    releaseDate = Date(),
                    voteAverage = 5.0,
                )
            )
        )
    }
}

@Composable
fun MovieGridLayout(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    movies: List<MovieUiModel>,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = dimensionResource(id = R.dimen.card_movie_width)),
        horizontalArrangement = Arrangement.spacedBy(Spacing.lg),
        verticalArrangement = Arrangement.spacedBy(Spacing.lg),
    ) {
        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            SectionMovieLayout(title = titleRes)
        }
        items(movies) { uiModel ->
            MovieCardLayout(
                modifier = Modifier.width(dimensionResource(id = R.dimen.card_movie_width)),
                uiModel = uiModel
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MovieGridLayoutPreview() {
    MovieGridLayout(
        titleRes = R.string.section_movie_popular,
        movies = listOf(
            MovieUiModel(
                id = 0L,
                title = "Thor: Love and Thunder",
                posterUrl = "https://image.tmdb.org/t/p/w185/6OEBp0Gqv6DsOgi8diPUslT2kbA.jpg",
                releaseDate = Date(),
                voteAverage = 5.0,
            )
        )
    )
}
