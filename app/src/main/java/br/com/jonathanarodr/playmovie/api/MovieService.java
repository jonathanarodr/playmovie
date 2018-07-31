package br.com.jonathanarodr.playmovie.api;

import br.com.jonathanarodr.playmovie.model.MovieApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    String URI_QUERY_API_KEY = "api_key";

    @GET("popular")
    Call<MovieApi> searchPopularMovie(@Query(URI_QUERY_API_KEY) String apiKey);

    @GET("top_rated")
    Call<MovieApi> searchTopRatedMovie(@Query(URI_QUERY_API_KEY) String apiKey);

}
