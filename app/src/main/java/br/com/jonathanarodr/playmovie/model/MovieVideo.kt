package br.com.jonathanarodr.playmovie.model

import com.google.gson.annotations.SerializedName

data class MovieVideo(
    @SerializedName("key")
    val key: String,
    @SerializedName("site")
    val site: String
) {
    companion object {
        private const val YOUTUBE_URL = "https://www.youtube.com/watch?v="
    }

    fun getUrlVideo() = YOUTUBE_URL + key
}
