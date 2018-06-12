package br.com.jonathanarodr.playmovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Movie implements Parcelable {

    private final String IMAGE_PATH = "http://image.tmdb.org/t/p";
    private final String IMAGE_SIZE_DEFAULT = "/w185";
    private final String IMAGE_SIZE_HIGHT = "/w780";

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("vote_average")
    private Double average;
    @SerializedName("release_date")
    private Date releaseDate;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster() {
        return IMAGE_PATH + IMAGE_SIZE_DEFAULT + poster;
    }

    public String getPosterHight() {
        return IMAGE_PATH + IMAGE_SIZE_HIGHT + poster;
    }

    public Double getAverage() {
        return average;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        overview = in.readString();
        poster = in.readString();
        average = in.readDouble();
        releaseDate = (Date) in.readSerializable();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(poster);
        dest.writeDouble(average);
        dest.writeSerializable(releaseDate);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

}
