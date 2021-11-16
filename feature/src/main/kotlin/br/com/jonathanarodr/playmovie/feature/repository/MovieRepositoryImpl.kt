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

    override suspend fun searchMovies(): List<Movie> {
        return remoteDataSource.searchMovies()
    }

    override suspend fun searchTvSeries(): List<Movie> {
        return remoteDataSource.searchTvSeries()
    }

    override suspend fun searchFavoriteMovies(): List<Movie> {
        return localDataSource.searchFavoriteMovies().map {
            it.toMovie()
        }
    }

    override suspend fun getFavoriteMovie(movieId: Long): Movie {
        return localDataSource.getFavoriteMovie(movieId).toMovie()
    }

    override suspend fun insertFavoriteMovie(movie: Movie) {
        localDataSource.insertFavoriteMovie(movie.toMovieEntity())
    }

    override suspend fun removeFavoriteMovie(movie: Movie) {
        localDataSource.removeFavoriteMovie(movie.toMovieEntity())
    }
}
