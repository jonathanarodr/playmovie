package br.com.jonathanarodr.playmovie.feature.domain.usecase

import androidx.test.filters.SmallTest
import br.com.jonathanarodr.playmovie.core.testing.rules.CoroutinesTestRule
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.domain.model.toMovieUiModel
import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.Date

@SmallTest
@ExperimentalCoroutinesApi
class MovieUseCaseTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val movies = listOf(
        Movie(
            id = 0L,
            title = "title",
            overview = "overview",
            poster = "poster",
            backdrop = "backdrop",
            average = 0.0,
            releaseDate = Date(),
            type = MovieType.MOVIES,
        )
    )

    private val result = Result.success(movies)
    private val expected = result.map { result -> result.map { it.toMovieUiModel() } }
    private val repository = mockk<MovieRepository>()
    private val useCase = MovieUseCase(repository)

    @Test
    fun `given use case when search movies then return list of movies`() {
        runTest {
            coEvery { repository.searchMovies() } returns result

            val result = useCase.searchMovies()

            assertEquals(expected.getOrThrow(), result.first())
            assertEquals(1, result.count())
        }
    }

    @Test
    fun `given use case when search tv series then return list of movies`() {
        runTest {
            coEvery { repository.searchTvSeries() } returns result

            val result = useCase.searchTvSeries()

            assertEquals(expected.getOrThrow(), result.first())
            assertEquals(1, result.count())
        }
    }

    @Test
    fun `given use case when search favorite movies then return list of movies`() {
        runTest {
            coEvery { repository.searchFavoriteMovies() } returns result

            val result = useCase.searchFavoriteMovies()

            assertEquals(expected.getOrThrow(), result.first())
            assertEquals(1, result.count())
        }
    }
}
