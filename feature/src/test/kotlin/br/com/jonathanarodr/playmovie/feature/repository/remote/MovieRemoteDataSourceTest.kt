package br.com.jonathanarodr.playmovie.feature.repository.remote

import androidx.test.filters.SmallTest
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.repository.remote.api.MovieApi
import br.com.jonathanarodr.playmovie.feature.repository.remote.api.MovieResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant
import java.util.Date

@SmallTest
@ExperimentalCoroutinesApi
class MovieRemoteDataSourceTest {

    private val api = mockk<MovieApi>()
    private val dataSource = MovieRemoteDataSource(api)
    private val movies = listOf(
        Movie(
            id = 0L,
            title = "title",
            overview = "overview",
            poster = "poster",
            backdrop = "backdrop",
            average = 0.0,
            releaseDate = Date.from(Instant.now()),
        )
    )
    private val response = mockk<MovieResponse> {
        every { results } returns movies
    }

    @Test
    fun `given datasource when search movies then call api with results`() {
        runTest {
            coEvery { api.searchMovies() } returns response

            val result = dataSource.searchMovies()

            assertEquals(movies, result)
        }
    }

    @Test
    fun `given datasource when search tv series then call api with results`() {
        runTest {
            coEvery { api.searchTvSeries() } returns response

            val result = dataSource.searchTvSeries()

            assertEquals(movies, result)
        }
    }
}
