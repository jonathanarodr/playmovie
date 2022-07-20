package br.com.jonathanarodr.playmovie.common.uikit.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.jonathanarodr.playmovie.common.uikit.token.Color
import br.com.jonathanarodr.playmovie.common.uikit.token.FontSize
import br.com.jonathanarodr.playmovie.common.uikit.token.ShapeSize

private val ColorPalette = lightColors(
    primary = Color.highlightRed,
    onPrimary = Color.neutralWhite,
    background = Color.grayscale400,
    onBackground = Color.grayscale100,
    surface = Color.grayscale100,
    onSurface = Color.grayscale400,
)

val Shapes = Shapes(
    small = RoundedCornerShape(ShapeSize.sm),
    medium = RoundedCornerShape(ShapeSize.md),
    large = RoundedCornerShape(ShapeSize.lg)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSize.xxl,
    ),
    h2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSize.xl,
    ),
    h3 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSize.lg,
    ),
    h4 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSize.md,
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = FontSize.sm,
    ),
    subtitle2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = FontSize.xs,
    ),
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FontSize.sm,
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FontSize.xs,
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FontSize.xxs,
    )
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = ColorPalette,
        shapes = Shapes,
        typography = Typography,
        content = content
    )
}
