package br.com.jonathanarodr.playmovie.feature.repository.local

import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieDao
import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieEntity

class MovieLocalDataSource(
    private val movieDao: MovieDao,
) {

    suspend fun fetchFavoriteMovies(): List<MovieEntity> {
        return movieDao.listFavoriteMovies()
    }

    suspend fun insertFavoriteMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    suspend fun removeFavoriteMovie(movie: MovieEntity) {
        movieDao.deleteMovie(movie)
    }
}