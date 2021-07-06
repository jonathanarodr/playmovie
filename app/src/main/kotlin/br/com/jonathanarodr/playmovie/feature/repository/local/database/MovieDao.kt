package br.com.jonathanarodr.playmovie.feature.repository.local.database

import androidx.room.*

@Dao
interface MovieDao {

    @Query("select * from movies order by average desc")
    suspend fun fetchFavoriteMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)
}