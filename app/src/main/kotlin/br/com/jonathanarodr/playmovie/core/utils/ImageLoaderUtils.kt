package br.com.jonathanarodr.playmovie.core.utils

import android.widget.ImageView
import br.com.jonathanarodr.playmovie.R
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class GlideUtils : AppGlideModule()

object ImageLoaderUtils {

    private const val IMAGE_LOADER_PATH = "http://image.tmdb.org/t/p"
    private const val IMAGE_SIZE = "/w185"

    fun load(view: ImageView, image: String) {
        GlideApp.with(view.context)
            .load(image.toPath())
            .centerCrop()
            .placeholder(R.drawable.backgroud_grey)
            .into(view)
    }

    private fun String.toPath(): String {
        return IMAGE_LOADER_PATH + IMAGE_SIZE + this
    }
}