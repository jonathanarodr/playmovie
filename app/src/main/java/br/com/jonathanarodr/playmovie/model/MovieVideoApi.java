package br.com.jonathanarodr.playmovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieVideoApi {

    @SerializedName("results")
    private List<MovieVideo> videos;

    public List<MovieVideo> getVideos() {
        return videos;
    }

}
