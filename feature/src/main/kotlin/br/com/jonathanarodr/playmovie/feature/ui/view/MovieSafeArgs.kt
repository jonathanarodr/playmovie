package br.com.jonathanarodr.playmovie.feature.ui.view

import android.os.Parcelable
import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieSafeArgs(
    val id: Long,
    val type: MovieType,
) : Parcelable
