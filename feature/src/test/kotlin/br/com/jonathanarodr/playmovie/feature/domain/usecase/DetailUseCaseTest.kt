package br.com.jonathanarodr.playmovie.feature.domain.usecase

import androidx.test.filters.SmallTest
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@SmallTest
@ExperimentalCoroutinesApi
class DetailUseCaseTest {

    private val movie = mockk<Movie>()
    private val repository = mockk<MovieRepository>(relaxed = true)
    private val useCase = DetailUseCase(repository)

    @Test
    fun `given use case when insert favorite movie then call repository`() {
        runTest {
            useCase.insertFavoriteMovie(movie)

            coVerify {
                repository.insertFavoriteMovie(
                    eq(movie)
                )
            }
        }
    }

    @Test
    fun `given use case when remove favorite movie then call repository`() {
        runTest {
            useCase.removeFavoriteMovie(movie)

            coVerify {
                repository.removeFavoriteMovie(
                    eq(movie)
                )
            }
        }
    }
}
