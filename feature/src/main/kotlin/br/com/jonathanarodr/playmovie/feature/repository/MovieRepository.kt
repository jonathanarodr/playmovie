package br.com.jonathanarodr.playmovie.feature.repository

import br.com.jonathanarodr.playmovie.feature.domain.model.Movie

interface MovieRepository {

    suspend fun searchMovies(): List<Movie>

    suspend fun searchTvSeries(): List<Movie>

    suspend fun searchFavoriteMovies(): List<Movie>

    suspend fun getFavoriteMovie(movieId: Long): Movie

    suspend fun insertFavoriteMovie(movie: Movie)

    suspend fun removeFavoriteMovie(movie: Movie)
}
