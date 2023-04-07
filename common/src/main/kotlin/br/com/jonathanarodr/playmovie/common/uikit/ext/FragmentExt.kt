package br.com.jonathanarodr.playmovie.common.uikit.ext

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import br.com.jonathanarodr.playmovie.common.uikit.theme.AppTheme

fun Fragment.setContent(
    strategy: ViewCompositionStrategy = ViewCompositionStrategy.DisposeOnLifecycleDestroyed(
        viewLifecycleOwner
    ),
    content: @Composable () -> Unit,
): ComposeView = ComposeView(requireContext()).apply {
    setViewCompositionStrategy(strategy)
    setContent {
        AppTheme {
            content()
        }
    }
}