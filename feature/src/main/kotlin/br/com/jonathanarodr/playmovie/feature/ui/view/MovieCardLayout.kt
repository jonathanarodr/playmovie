package br.com.jonathanarodr.playmovie.feature.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
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
import br.com.jonathanarodr.playmovie.common.utils.ImageLoaderUtils.IMAGE_LOADER_PATH
import br.com.jonathanarodr.playmovie.common.utils.ImageLoaderUtils.IMAGE_SIZE_HIGH
import br.com.jonathanarodr.playmovie.common.utils.format
import br.com.jonathanarodr.playmovie.feature.R
import br.com.jonathanarodr.playmovie.feature.ui.model.MovieUiModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.util.*

@ExperimentalMaterial3Api
@Composable
fun MovieCardLayout(
    modifier: Modifier = Modifier,
    uiModel: MovieUiModel,
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
    ) {
        // fixme create composable to centralize image loader with dynamic size
        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(IMAGE_LOADER_PATH + IMAGE_SIZE_HIGH + uiModel.posterUrl)
            .crossfade(true)
            .placeholder(drawableResId = R.drawable.shape_card_movie)
            .fallback(drawableResId = R.drawable.shape_card_movie)

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.card_movie_height))
                .clip(MaterialTheme.shapes.large),
            model = imageRequest.build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Spacing.xs),
            text = uiModel.title,
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.grayscale100,
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Spacing.xxs),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            uiModel.voteAverage?.let {
                Text(
                    modifier = Modifier,
                    text = uiModel.voteAverage.toString(),
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = Color.grayscale200,
                    )
                )
                Image(
                    modifier = Modifier
                        .padding(start = Spacing.xxs, end = Spacing.sm)
                        .size(10.dp)
                        .wrapContentSize(),
                    painter = painterResource(id = R.drawable.ic_star),
                    colorFilter = ColorFilter.tint(color = Color.grayscale200),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier,
                text = uiModel.releaseDate.format(DATE_PATTERN_DD_MM_YYYY),
                style = MaterialTheme.typography.labelLarge.copy(
                    color = Color.grayscale200,
                )
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
@Preview
fun MovieCardLayoutPreview() {
    AppTheme {
        MovieCardLayout(
            modifier = Modifier
                .width(120.dp)
                .wrapContentHeight(),
            uiModel = MovieUiModel(
                id = 0L,
                title = "Thor: Love and Thunder",
                posterUrl = "https://image.tmdb.org/t/p/w185/6OEBp0Gqv6DsOgi8diPUslT2kbA.jpg",
                releaseDate = Date(),
                voteAverage = 5.0,
            )
        )
    }
}
