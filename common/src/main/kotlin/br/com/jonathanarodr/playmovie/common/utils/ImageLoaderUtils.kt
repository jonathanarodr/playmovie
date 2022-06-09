package br.com.jonathanarodr.playmovie.common.utils

import android.widget.ImageView
import coil.load

typealias ImageSize = String

object ImageLoaderUtils {

    private const val IMAGE_LOADER_PATH = "http://image.tmdb.org/t/p"

    const val IMAGE_SIZE_DEFAULT: ImageSize = "/w185"
    const val IMAGE_SIZE_HIGH: ImageSize = "/w780"

    fun load(view: ImageView, url: String, size: ImageSize = IMAGE_SIZE_DEFAULT) {
        view.load(url.toPath(size)) {
            crossfade(true)
        }
    }

    private fun String.toPath(size: ImageSize): String {
        return IMAGE_LOADER_PATH + size + this
    }
}
