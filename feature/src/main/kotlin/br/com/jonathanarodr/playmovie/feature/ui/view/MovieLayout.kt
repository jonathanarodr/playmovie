package br.com.jonathanarodr.playmovie.feature.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.jonathanarodr.playmovie.common.uikit.theme.AppTheme
import br.com.jonathanarodr.playmovie.common.uikit.token.Color
import br.com.jonathanarodr.playmovie.common.uikit.token.Spacing
import br.com.jonathanarodr.playmovie.feature.R
import br.com.jonathanarodr.playmovie.feature.ui.model.MovieUiModel
import java.util.*

@ExperimentalMaterial3Api
@Composable
fun MovieCarouselLayout(
    modifier: Modifier = Modifier,
    movies: List<MovieUiModel>,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Spacing.lg),
        //contentPadding = PaddingValues(horizontal = Spacing.lg),
    ) {
        items(movies) { uiModel ->
            MovieCardLayout(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.card_movie_width))
                    .wrapContentHeight(),
                uiModel = uiModel
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
@Preview
fun MovieRowListLayoutPreview() {
    AppTheme {
        MovieCarouselLayout(
            movies = listOf(
                MovieUiModel(
                    id = 0L,
                    title = "Thor: Love and Thunder",
                    posterUrl = "https://image.tmdb.org/t/p/w185/6OEBp0Gqv6DsOgi8diPUslT2kbA.jpg",
                    releaseDate = Date(),
                    voteAverage = 5.0,
                )
            ),
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun MovieGridLayout(
    modifier: Modifier = Modifier,
    movies: List<MovieUiModel>,
    onClick: (MovieUiModel) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = dimensionResource(id = R.dimen.card_movie_width)),
        horizontalArrangement = Arrangement.spacedBy(Spacing.lg),
        verticalArrangement = Arrangement.spacedBy(Spacing.lg),
        contentPadding = PaddingValues(horizontal = Spacing.lg),
    ) {
        items(movies) { uiModel ->
            MovieCardLayout(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.card_movie_width))
                    .wrapContentHeight()
                    .clickable { onClick(uiModel) },
                uiModel = uiModel
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
@Preview
fun MovieGridLayoutPreview() {
    MovieGridLayout(
        modifier = Modifier.background(color = Color.neutralBack),
        movies = listOf(
            MovieUiModel(
                id = 0L,
                title = "Thor: Love and Thunder",
                posterUrl = "https://image.tmdb.org/t/p/w185/6OEBp0Gqv6DsOgi8diPUslT2kbA.jpg",
                releaseDate = Date(),
                voteAverage = 5.0,
            )
        ),
        onClick = {},
    )
}
