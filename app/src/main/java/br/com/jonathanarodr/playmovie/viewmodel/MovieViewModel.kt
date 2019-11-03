package br.com.jonathanarodr.playmovie.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import br.com.jonathanarodr.playmovie.api.ApiService.instance
import br.com.jonathanarodr.playmovie.api.MovieService
import br.com.jonathanarodr.playmovie.data.AppDatabase.Companion.getInstance
import br.com.jonathanarodr.playmovie.data.MovieRepository
import br.com.jonathanarodr.playmovie.model.Movie
import br.com.jonathanarodr.playmovie.model.MovieReview
import br.com.jonathanarodr.playmovie.model.MovieVideo

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository: MovieRepository

    init {
        val service = instance.create(MovieService::class.java)
        val dao = getInstance(getApplication()).movieDao()
        movieRepository = MovieRepository(service, dao)
    }

    fun getPopularMovies(): LiveData<List<Movie>> {
        return this.movieRepository.searchPopularMovies()
    }

    fun getTopRatedMovies(): LiveData<List<Movie>> {
        return this.movieRepository.searchTopRatedMovies()
    }

    fun getVideos(movieId: Int): LiveData<List<MovieVideo>> {
        return this.movieRepository.searchVideos(movieId)
    }

    fun getReviews(movieId: Int): LiveData<List<MovieReview>> {
        return this.movieRepository.searchReviews(movieId)
    }

    fun getFavoriteMovies(): LiveData<List<Movie>> {
        return this.movieRepository.searchFavoriteMovies()
    }

    fun insertFavoriteMovie(movie: Movie) {
        movieRepository.insertFavoriteMovie(movie)
    }

    fun deleteFavoriteMovie(movie: Movie) {
        movieRepository.deleteFavoriteMovie(movie)
    }
}