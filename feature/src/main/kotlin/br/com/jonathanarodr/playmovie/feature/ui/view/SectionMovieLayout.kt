package br.com.jonathanarodr.playmovie.feature.ui.view

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.jonathanarodr.playmovie.common.uikit.theme.AppTheme
import br.com.jonathanarodr.playmovie.common.uikit.token.Color
import br.com.jonathanarodr.playmovie.common.uikit.token.Spacing
import br.com.jonathanarodr.playmovie.feature.R

@Composable
fun SectionMovieLayout(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
) {
    Row(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = Spacing.lg),
            text = stringResource(id = title),
            style = MaterialTheme.typography.headlineSmall.copy(
                color = Color.neutralWhite,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SectionMovieLayoutPreview() {
    AppTheme {
        SectionMovieLayout(title = R.string.section_movie_popular)
    }
}
