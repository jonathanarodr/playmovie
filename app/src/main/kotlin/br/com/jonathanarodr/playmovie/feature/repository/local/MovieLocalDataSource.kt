package br.com.jonathanarodr.playmovie.feature.repository.local

import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieDao
import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieLocalDataSource(
    private val movieDao: MovieDao,
) {

    suspend fun searchFavoriteMovies(): List<MovieEntity> {
        return withContext(Dispatchers.IO) {
            movieDao.listMovies()
        }
    }

    suspend fun getFavoriteMovie(movieId: Long): MovieEntity {
        return withContext(Dispatchers.IO) {
            movieDao.selectMovie(movieId)
        }
    }

    suspend fun insertFavoriteMovie(movie: MovieEntity) {
        return withContext(Dispatchers.IO) {
            movieDao.insertMovie(movie)
        }
    }

    suspend fun removeFavoriteMovie(movie: MovieEntity) {
        return withContext(Dispatchers.IO) {
            movieDao.deleteMovie(movie)
        }
    }
}