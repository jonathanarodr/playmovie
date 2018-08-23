package br.com.jonathanarodr.playmovie.model;

import com.google.gson.annotations.SerializedName;

public class MovieVideo {

    private static final String YOUTUBE_URL = "https://www.youtube.com/watch?v=";

    @SerializedName("key")
    private String key;
    @SerializedName("site")
    private String site;

    public String getKey() {
        return YOUTUBE_URL + key;
    }

    public String getSite() {
        return site;
    }

}
