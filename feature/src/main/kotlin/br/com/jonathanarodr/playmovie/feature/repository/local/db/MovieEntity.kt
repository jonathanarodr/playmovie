package br.com.jonathanarodr.playmovie.feature.repository.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

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
