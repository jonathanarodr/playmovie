package br.com.jonathanarodr.playmovie.feature.repository.local

import br.com.jonathanarodr.playmovie.common.executors.StorageExecutor
import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieDao
import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieEntity

class MovieLocalDataSource(
    private val movieDao: MovieDao,
) : StorageExecutor() {

    suspend fun searchFavoriteMovies(): Result<List<MovieEntity>> {
        return execute {
            movieDao.listMovies()
        }
    }

    suspend fun getFavoriteMovie(movieId: Long): Result<MovieEntity> {
        return execute {
            movieDao.selectMovie(movieId)
        }
    }

    suspend fun insertFavoriteMovie(movie: MovieEntity): Result<Unit> {
        return execute {
            movieDao.insertMovie(movie)
        }
    }

    suspend fun removeFavoriteMovie(movie: MovieEntity): Result<Unit> {
        return execute {
            movieDao.deleteMovie(movie)
        }
    }
}
