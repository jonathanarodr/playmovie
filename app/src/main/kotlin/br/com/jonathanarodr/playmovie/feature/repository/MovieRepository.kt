package br.com.jonathanarodr.playmovie.feature.repository

import br.com.jonathanarodr.playmovie.feature.domain.model.Movie

interface MovieRepository {

    suspend fun searchMovies(): Result<List<Movie>>

    suspend fun searchTvSeries(): Result<List<Movie>>

    suspend fun fetchFavoriteMovies(): Result<List<Movie>>

    suspend fun insertFavoriteMovie(movie: Movie)

    suspend fun removeFavoriteMovie(movie: Movie)
}