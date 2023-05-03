package br.com.jonathanarodr.playmovie.feature.domain.model

import androidx.test.filters.SmallTest
import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType
import br.com.jonathanarodr.playmovie.feature.repository.local.db.MovieEntity
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant
import java.util.Date

@SmallTest
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
        type = MovieType.MOVIES,
    )

    private val expected = MovieEntity(
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
    fun `given model when convert to entity then return valid movie entity`() {
        val result = movie.toMovieEntity()

        assertEquals(expected, result)
    }
}
