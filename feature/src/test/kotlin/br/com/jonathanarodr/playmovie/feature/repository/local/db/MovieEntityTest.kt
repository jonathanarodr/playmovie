package br.com.jonathanarodr.playmovie.feature.repository.local.db

import androidx.test.filters.SmallTest
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant
import java.util.Date

@SmallTest
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
        type = MovieType.MOVIES,
    )

    private val expected = Movie(
        id = 0L,
        title = "title",
        overview = "overview",
        poster = "poster",
        backdrop = "backdrop",
        average = 0.0,
        releaseDate = date,
        type = MovieType.MOVIES,
    )

    @Test
    fun `given entity when convert to model then return valid movie model`() {
        val result = entity.toMovie()

        assertEquals(expected, result)
    }
}
