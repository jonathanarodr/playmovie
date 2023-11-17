package br.com.jonathanarodr.playmovie.feature.repository

import br.com.jonathanarodr.playmovie.feature.domain.model.Movie

interface MovieRepository {

    suspend fun searchMovies(): Result<List<Movie>>

    suspend fun searchTvSeries(): Result<List<Movie>>

    suspend fun getMovieDetail(id: Long): Result<Movie>

    suspend fun getTvSerieDetail(id: Long): Result<Movie>

    suspend fun searchFavoriteMovies(): Result<List<Movie>>

    suspend fun getFavoriteMovie(id: Long): Result<Movie>

    suspend fun insertFavoriteMovie(movie: Movie): Result<Unit>

    suspend fun removeFavoriteMovie(movie: Movie): Result<Unit>
}
