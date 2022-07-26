package br.com.jonathanarodr.playmovie.common.uikit.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import br.com.jonathanarodr.playmovie.common.uikit.token.Color
import br.com.jonathanarodr.playmovie.common.uikit.token.FontSize
import br.com.jonathanarodr.playmovie.common.uikit.token.ShapeSize

private val ColorScheme = lightColorScheme(
    primary = Color.highlightRed,
    onPrimary = Color.neutralWhite,
    background = Color.neutralBack,
    onBackground = Color.neutralWhite,
    surface = Color.grayscale400,
    onSurface = Color.grayscale100,
    error = Color.highlightRed,
    onError = Color.neutralWhite,
)

val ShapeScheme = Shapes(
    small = RoundedCornerShape(ShapeSize.sm),
    medium = RoundedCornerShape(ShapeSize.md),
    large = RoundedCornerShape(ShapeSize.lg)
)

val TypographyScheme = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSize.xxl,
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSize.xxl,
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSize.xxl,
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSize.xl,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSize.lg,
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSize.md,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = FontSize.sm,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = FontSize.xs,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = FontSize.xxs,
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FontSize.sm,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FontSize.xs,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FontSize.xxs,
    ),
    labelLarge = TextStyle(
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
        colorScheme = ColorScheme,
        shapes = ShapeScheme,
        typography = TypographyScheme,
        content = content
    )
}
