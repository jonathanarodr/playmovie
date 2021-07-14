package br.com.jonathanarodr.playmovie.core.utils

import android.widget.ImageView
import br.com.jonathanarodr.playmovie.R
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class GlideUtils : AppGlideModule()

typealias ImageSize = String

object ImageLoaderUtils {

    private const val IMAGE_LOADER_PATH = "http://image.tmdb.org/t/p"

    const val IMAGE_SIZE_DEFAULT: ImageSize = "/w185"
    const val IMAGE_SIZE_HIGH: ImageSize = "/w780"

    fun load(view: ImageView, image: String, size: ImageSize = IMAGE_SIZE_DEFAULT) {
        GlideApp.with(view.context)
            .load(image.toPath(size))
            .centerCrop()
            .placeholder(R.drawable.backgroud_grey)
            .into(view)
    }

    private fun String.toPath(size: ImageSize): String {
        return IMAGE_LOADER_PATH + size + this
    }
}