package br.com.jonathanarodr.playmovie.ui

import br.com.jonathanarodr.playmovie.model.Movie

interface MovieAdapterOnClickHandler {
    fun onClick(movie: Movie)
}