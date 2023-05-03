package br.com.jonathanarodr.playmovie.feature.domain.usecase

import androidx.test.filters.SmallTest
import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType
import br.com.jonathanarodr.playmovie.feature.repository.MovieRepository
import br.com.jonathanarodr.playmovie.feature.ui.model.DetailUiModel
import br.com.jonathanarodr.playmovie.feature.ui.model.toMovie
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.util.Date

@SmallTest
@ExperimentalCoroutinesApi
class DetailUseCaseTest {

    private val repository = mockk<MovieRepository>(relaxed = true)
    private val useCase = DetailUseCase(repository)

    private val uiModel = DetailUiModel(
        id = 0L,
        title = "title",
        overview = "overview",
        poster = "poster",
        backdrop = "backdrop",
        average = 0.0,
        releaseDate = Date(),
        type = MovieType.MOVIES,
        isFavorite = true,
    )

    @Test
    fun `given use case when insert favorite movie then call repository`() {
        runTest {
            useCase.insertFavoriteMovie(uiModel)

            coVerify {
                repository.insertFavoriteMovie(
                    eq(uiModel.toMovie())
                )
            }
        }
    }

    @Test
    fun `given use case when remove favorite movie then call repository`() {
        runTest {
            useCase.removeFavoriteMovie(uiModel)

            coVerify {
                repository.removeFavoriteMovie(
                    eq(uiModel.toMovie())
                )
            }
        }
    }
}
