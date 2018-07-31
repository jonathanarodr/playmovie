package br.com.jonathanarodr.playmovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieApi {

    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

}
