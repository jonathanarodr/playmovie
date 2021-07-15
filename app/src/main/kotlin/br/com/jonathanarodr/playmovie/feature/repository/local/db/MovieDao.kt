package br.com.jonathanarodr.playmovie.feature.repository.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("select * from movies order by average desc")
    suspend fun listMovies(): List<MovieEntity>

    @Query("select * from movies where id = :movieId")
    suspend fun selectMovie(movieId: Long): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)
}