package br.com.jonathanarodr.playmovie.api;

import br.com.jonathanarodr.playmovie.model.Movie;
import br.com.jonathanarodr.playmovie.model.MovieReview;
import br.com.jonathanarodr.playmovie.model.MovieVideo;
import br.com.jonathanarodr.playmovie.model.ResultApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    String URI_QUERY_API_KEY = "api_key";

    @GET("popular")
    Call<ResultApi<Movie>> searchPopularMovies(@Query(URI_QUERY_API_KEY) String apiKey);

    @GET("top_rated")
    Call<ResultApi<Movie>> searchTopRatedMovies(@Query(URI_QUERY_API_KEY) String apiKey);

    @GET("{id}/videos")
    Call<ResultApi<MovieVideo>> searchVideos(@Path("id") int id, @Query(URI_QUERY_API_KEY) String apiKey);

    @GET("{id}/reviews")
    Call<ResultApi<MovieReview>> searchReviews(@Path("id") int id, @Query(URI_QUERY_API_KEY) String apiKey);

}
