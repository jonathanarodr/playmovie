package br.com.jonathanarodr.playmovie.feature.domain.usecase

import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieUseCaseTest {

    private val movies = mockk<List<Movie>>()
    private val expected = Result.success(movies)
    private val repository = mockk<MovieRepository>()
    private val useCase = MovieUseCase(repository)

    @Test
    fun `given use case when search movies then return list of movies`() {
        runBlocking {
            coEvery { repository.searchMovies() } returns movies

            val result = useCase.searchMovies()

            assertEquals(expected, result)
        }
    }

    @Test
    fun `given use case when search tv series then return list of movies`() {
        runBlocking {
            coEvery { repository.searchTvSeries() } returns movies

            val result = useCase.searchTvSeries()

            assertEquals(expected, result)
        }
    }

    @Test
    fun `given use case when search favorite movies then return list of movies`() {
        runBlocking {
            coEvery { repository.searchFavoriteMovies() } returns movies

            val result = useCase.searchFavoriteMovies()

            assertEquals(expected, result)
        }
    }
}