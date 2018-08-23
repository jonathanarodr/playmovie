package br.com.jonathanarodr.playmovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieReviewApi {

    @SerializedName("results")
    private List<MovieReview> reviews;

    public List<MovieReview> getReviews() {
        return reviews;
    }

}
