package br.com.jonathanarodr.playmovie.feature.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.jonathanarodr.playmovie.common.annotation.DATE_PATTERN_DD_MM_YYYY
import br.com.jonathanarodr.playmovie.common.uikit.theme.AppTheme
import br.com.jonathanarodr.playmovie.common.uikit.token.Color
import br.com.jonathanarodr.playmovie.common.uikit.token.Spacing
import br.com.jonathanarodr.playmovie.common.utils.format
import br.com.jonathanarodr.playmovie.feature.R
import br.com.jonathanarodr.playmovie.feature.ui.model.MovieUiModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.util.*

@Composable
fun MovieCardLayout(
    modifier: Modifier = Modifier,
    uiModel: MovieUiModel,
) {
    Column(
        modifier = modifier,
    ) {
        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(uiModel.posterUrl)
            .crossfade(true)
            .fallback(drawableResId = R.drawable.shape_card_movie)

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.card_movie_height))
                .padding(vertical = Spacing.xs),
            model = imageRequest.build(),
            contentDescription = null,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = uiModel.title,
            style = MaterialTheme.typography.subtitle2.copy(
                color = Color.grayscale100,
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            uiModel.voteAverage?.let {
                Text(
                    modifier = Modifier,
                    text = uiModel.voteAverage.toString(),
                    style = MaterialTheme.typography.caption.copy(
                        color = Color.grayscale200,
                    )
                )
                Image(
                    modifier = Modifier.padding(end = Spacing.xs)
                        .size(8.dp)
                        .wrapContentSize(),
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier,
                text = uiModel.releaseDate.format(DATE_PATTERN_DD_MM_YYYY),
                style = MaterialTheme.typography.caption.copy(
                    color = Color.grayscale200,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCardLayoutPreview() {
    AppTheme {
        MovieCardLayout(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.card_movie_width))
                .background(color = Color.neutralBack),
            uiModel = MovieUiModel(
                id = 0L,
                title = "Thor: Love and Thunder",
                posterUrl = "https://api.themoviedb.org/6OEBp0Gqv6DsOgi8diPUslT2kbA.jpg",
                releaseDate = Date(),
                voteAverage = 5.0,
            )
        )
    }
}
