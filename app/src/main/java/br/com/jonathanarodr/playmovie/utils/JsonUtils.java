package br.com.jonathanarodr.playmovie.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import br.com.jonathanarodr.playmovie.model.Movie;

public class JsonUtils {

    public static List<Movie> fromJson(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        Type type = new TypeToken<List<Movie>>() {}.getType();

        return new Gson().fromJson(jsonObject.get("results"), type);
    }

}
