package br.com.jonathanarodr.playmovie.model

import com.google.gson.annotations.SerializedName

class ResultApi<T> {

    @SerializedName("results")
    val results: List<T>? = null

}