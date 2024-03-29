package br.com.jonathanarodr.playmovie.feature.domain.usecase

import androidx.test.filters.SmallTest
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@SmallTest
@ExperimentalCoroutinesApi
class MovieUseCaseTest {

    private val movies = mockk<List<Movie>>()
    private val expected = Result.success(movies)
    private val repository = mockk<MovieRepository>()
    private val useCase = MovieUseCase(repository)

    @Test
    fun `given use case when search movies then return list of movies`() {
        runTest {
            coEvery { repository.searchMovies() } returns expected

            val result = useCase.searchMovies()

            assertEquals(expected, result)
        }
    }

    @Test
    fun `given use case when search tv series then return list of movies`() {
        runTest {
            coEvery { repository.searchTvSeries() } returns expected

            val result = useCase.searchTvSeries()

            assertEquals(expected, result)
        }
    }

    @Test
    fun `given use case when search favorite movies then return list of movies`() {
        runTest {
            coEvery { repository.searchFavoriteMovies() } returns expected

            val result = useCase.searchFavoriteMovies()

            assertEquals(expected, result)
        }
    }
}
