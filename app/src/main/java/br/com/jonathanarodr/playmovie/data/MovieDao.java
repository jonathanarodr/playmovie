package br.com.jonathanarodr.playmovie.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.jonathanarodr.playmovie.model.Movie;

@Dao
public interface MovieDao {

    @Query("select * from movies order by title")
    LiveData<List<Movie>> listMovies();

    @Insert
    void insertMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);

}
