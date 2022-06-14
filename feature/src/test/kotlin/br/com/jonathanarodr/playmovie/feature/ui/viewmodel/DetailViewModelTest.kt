package br.com.jonathanarodr.playmovie.feature.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.MediumTest
import br.com.jonathanarodr.playmovie.common.states.UiState
import br.com.jonathanarodr.playmovie.core.testing.ext.capture
import br.com.jonathanarodr.playmovie.core.testing.rules.CoroutinesTestRule
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.domain.usecase.DetailUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@MediumTest
@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val movie = mockk<Movie>()
    private val useCase = mockk<DetailUseCase>()
    private val viewModel = DetailViewModel(useCase)

    @Test
    fun `given view model when insert favorite movie with error then post ui state error`() {
        runTest {
            coEvery { useCase.insertFavoriteMovie(movie) } returns Result.failure(mockk())

            viewModel.insertFavoriteMovie(movie)

            val result = viewModel.insertedMovie.capture()

            assertThat(result).isInstanceOf(UiState.Error::class.java)
        }
    }

    @Test
    fun `given view model when insert favorite movie with success then post ui state success`() {
        runTest {
            coEvery { useCase.insertFavoriteMovie(movie) } returns Result.success(mockk())

            viewModel.insertFavoriteMovie(movie)

            val result = viewModel.insertedMovie.capture()

            assertThat(result).isInstanceOf(UiState.Success::class.java)
        }
    }

    @Test
    fun `given view model when remove favorite movie with error then post ui state error`() {
        runTest {
            coEvery { useCase.removeFavoriteMovie(movie) } returns Result.failure(mockk())

            viewModel.removeFavoriteMovie(movie)

            val result = viewModel.removedMovie.capture()

            assertThat(result).isInstanceOf(UiState.Error::class.java)
        }
    }

    @Test
    fun `given view model when remove favorite movie with success then post ui state success`() {
        runTest {
            coEvery { useCase.removeFavoriteMovie(movie) } returns Result.success(mockk())

            viewModel.removeFavoriteMovie(movie)

            val result = viewModel.removedMovie.capture()

            assertThat(result).isInstanceOf(UiState.Success::class.java)
        }
    }
}
