package br.com.jonathanarodr.playmovie.feature.repository.local.db

import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant
import java.util.*

class MovieEntityTest {

    private val date = Date.from(Instant.now())

    private val entity = MovieEntity(
        id = 0L,
        title = "title",
        overview = "overview",
        poster = "poster",
        backdrop = "backdrop",
        average = 0.0,
        releaseDate = date,
    )

    private val expected = Movie(
        id = 0L,
        title = "title",
        overview = "overview",
        poster = "poster",
        backdrop = "backdrop",
        average = 0.0,
        releaseDate = date,
    )

    @Test
    fun `given entity when convert to model then return valid movie model`() {
        val result = entity.toMovie()

        assertEquals(expected, result)
    }
}