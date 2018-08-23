package br.com.jonathanarodr.playmovie.api;

import br.com.jonathanarodr.playmovie.model.MovieApi;
import br.com.jonathanarodr.playmovie.model.MovieReviewApi;
import br.com.jonathanarodr.playmovie.model.MovieVideoApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    String URI_QUERY_API_KEY = "api_key";

    @GET("popular")
    Call<MovieApi> searchPopularMovies(@Query(URI_QUERY_API_KEY) String apiKey);

    @GET("top_rated")
    Call<MovieApi> searchTopRatedMovies(@Query(URI_QUERY_API_KEY) String apiKey);

    @GET("{id}/videos")
    Call<MovieVideoApi> searchVideos(@Path("id") int id, @Query(URI_QUERY_API_KEY) String apiKey);

    @GET("{id}/reviews")
    Call<MovieReviewApi> searchReviews(@Path("id") int id, @Query(URI_QUERY_API_KEY) String apiKey);

}
