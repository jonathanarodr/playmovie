package br.com.jonathanarodr.playmovie.feature.repository.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {

    @Query("select * from movies order by average desc")
    suspend fun listMovies(): List<MovieEntity>

    @Query("select * from movies where id = :id")
    suspend fun selectMovie(id: Long): MovieEntity

    @Upsert
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)
}
