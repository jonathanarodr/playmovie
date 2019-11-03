package br.com.jonathanarodr.playmovie.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.jonathanarodr.playmovie.model.Movie

@Dao
interface MovieDao {
    @Query("select * from movies order by title")
    fun listMovies(): LiveData<List<Movie>>

    @Insert
    fun insertMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)
}