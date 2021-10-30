package br.com.jonathanarodr.playmovie.feature.domain.model

import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieEntity
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant
import java.util.Date

class MovieTest {

    private val date = Date.from(Instant.now())

    private val movie = Movie(
        id = 0L,
        title = "title",
        overview = "overview",
        poster = "poster",
        backdrop = "backdrop",
        average = 0.0,
        releaseDate = date,
    )

    private val expected = MovieEntity(
        id = 0L,
        title = "title",
        overview = "overview",
        poster = "poster",
        backdrop = "backdrop",
        average = 0.0,
        releaseDate = date,
    )

    @Test
    fun `given model when convert to entity then return valid movie entity`() {
        val result = movie.toMovieEntity()

        assertEquals(expected, result)
    }
}
