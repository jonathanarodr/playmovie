package br.com.jonathanarodr.playmovie.common.uikit.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import br.com.jonathanarodr.playmovie.common.uikit.token.Color

private val ColorPalette = lightColors(
    primary = Color.highlightRed,
    onPrimary = Color.neutralWhite,
    background = Color.grayscale400,
    onBackground = Color.grayscale100,
    surface = Color.grayscale100,
    onSurface = Color.grayscale400,
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = ColorPalette,
        content = content
    )
}
