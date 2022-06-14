package br.com.jonathanarodr.playmovie.feature.repository

import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.domain.model.toMovieEntity
import br.com.jonathanarodr.playmovie.feature.repository.local.MovieLocalDataSource
import br.com.jonathanarodr.playmovie.feature.repository.local.db.toMovie
import br.com.jonathanarodr.playmovie.feature.repository.remote.MovieRemoteDataSource

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
) : MovieRepository {

    override suspend fun searchMovies(): Result<List<Movie>> {
        return remoteDataSource.searchMovies()
    }

    override suspend fun searchTvSeries(): Result<List<Movie>> {
        return remoteDataSource.searchTvSeries()
    }

    override suspend fun searchFavoriteMovies(): Result<List<Movie>> {
        return localDataSource.searchFavoriteMovies().mapCatching { result ->
            result.map { it.toMovie() }
        }
    }

    override suspend fun getFavoriteMovie(movieId: Long): Result<Movie> {
        return localDataSource.getFavoriteMovie(movieId).mapCatching { result ->
            result.toMovie()
        }
    }

    override suspend fun insertFavoriteMovie(movie: Movie): Result<Unit> {
        return localDataSource.insertFavoriteMovie(movie.toMovieEntity())
    }

    override suspend fun removeFavoriteMovie(movie: Movie): Result<Unit> {
        return localDataSource.removeFavoriteMovie(movie.toMovieEntity())
    }
}
