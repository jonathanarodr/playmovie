package br.com.jonathanarodr.playmovie.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.jonathanarodr.playmovie.BuildConfig
import br.com.jonathanarodr.playmovie.api.MovieService
import br.com.jonathanarodr.playmovie.model.Movie
import br.com.jonathanarodr.playmovie.model.MovieReview
import br.com.jonathanarodr.playmovie.model.MovieVideo
import br.com.jonathanarodr.playmovie.model.ResultApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(
    private val movieService: MovieService,
    private val movieDao: MovieDao
) {
    companion object {
        private val TAG = MovieRepository::class.java.simpleName
    }

    fun searchPopularMovies(): LiveData<List<Movie>> {
        val data = MutableLiveData<List<Movie>>()

        movieService.searchPopularMovies(BuildConfig.API_KEY)
            .enqueue(object : Callback<ResultApi<Movie>> {
                override fun onResponse(
                    call: Call<ResultApi<Movie>>,
                    response: Response<ResultApi<Movie>>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body()!!.results
                    }
                }

                override fun onFailure(call: Call<ResultApi<Movie>>, t: Throwable) {
                    Log.e(TAG, t.message ?: "")
                }
            })

        return data
    }

    fun searchTopRatedMovies(): LiveData<List<Movie>?> {
        val data = MutableLiveData<List<Movie>>()

        movieService.searchTopRatedMovies(BuildConfig.API_KEY)
            .enqueue(object : Callback<ResultApi<Movie>> {
                override fun onResponse(
                    call: Call<ResultApi<Movie>>,
                    response: Response<ResultApi<Movie>>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body()!!.results
                    }
                }

                override fun onFailure(call: Call<ResultApi<Movie>>, t: Throwable) {
                    Log.e(TAG, t.message ?: "Failure in request of top rated movies")
                }
            })

        return data
    }

    fun searchVideos(movieId: Int): LiveData<List<MovieVideo>?> {
        val data = MutableLiveData<List<MovieVideo>>()

        movieService.searchVideos(movieId, BuildConfig.API_KEY)
            .enqueue(object : Callback<ResultApi<MovieVideo>> {
                override fun onResponse(
                    call: Call<ResultApi<MovieVideo>>,
                    response: Response<ResultApi<MovieVideo>>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body()!!.results
                    }
                }

                override fun onFailure(
                    call: Call<ResultApi<MovieVideo>>,
                    t: Throwable
                ) {
                    Log.e(TAG, t.message ?: "Failure in request of videos")
                }
            })
        return data
    }

    fun searchReviews(movieId: Int): LiveData<List<MovieReview>> {
        val data = MutableLiveData<List<MovieReview>>()

        movieService.searchReviews(movieId, BuildConfig.API_KEY)
            .enqueue(object : Callback<ResultApi<MovieReview>> {
                override fun onResponse(
                    call: Call<ResultApi<MovieReview>>,
                    response: Response<ResultApi<MovieReview>>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body()!!.results
                    }
                }

                override fun onFailure(
                    call: Call<ResultApi<MovieReview>>, t: Throwable
                ) {
                    Log.e(TAG, t.message ?: "Failure in request of reviews")
                }
            })
        return data
    }

    fun searchFavoriteMovies(): LiveData<List<Movie>> {
        return movieDao.listMovies()
    }

    fun insertFavoriteMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    fun deleteFavoriteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }

}