package br.com.jonathanarodr.playmovie.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

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
