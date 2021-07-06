package br.com.jonathanarodr.playmovie.feature.repository.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import java.util.*

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "poster")
    val poster: String,
    @ColumnInfo(name = "backdrop")
    val backdrop: String,
    @ColumnInfo(name = "average")
    val average: Double,
    @ColumnInfo(name = "release")
    val releaseDate: Date,
)

fun MovieEntity.toMovie() = Movie(
    id = this.id,
    title = this.title,
    overview = this.overview,
    poster = this.poster,
    backdrop = this.backdrop,
    average = this.average,
    releaseDate = this.releaseDate,
)