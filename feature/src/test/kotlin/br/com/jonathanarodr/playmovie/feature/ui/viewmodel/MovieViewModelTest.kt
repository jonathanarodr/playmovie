package br.com.jonathanarodr.playmovie.feature.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.MediumTest
import br.com.jonathanarodr.playmovie.common.states.UiState
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.domain.type.MovieType
import br.com.jonathanarodr.playmovie.feature.domain.usecase.MovieUseCase
import br.com.jonathanarodr.playmovie.testing.ext.capture
import br.com.jonathanarodr.playmovie.testing.rules.CoroutinesTestRule
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@MediumTest
class MovieViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val resultError: Result<List<Movie>> = Result.failure(mockk())
    private val resultSuccess: Result<List<Movie>> = Result.success(listOf(mockk()))
    private val resultEmpty: Result<List<Movie>> = Result.success(emptyList())
    private val useCase = mockk<MovieUseCase>()
    private val viewModel = MovieViewModel(useCase)

    @Test
    fun `given view model when fetch movies with error then post ui state error`() {
        runTest {
            coEvery { useCase.searchMovies() } returns resultError

            viewModel.fetchMovies(MovieType.MOVIES)

            val result = viewModel.fetchMovies.capture()

            assertThat(result).isInstanceOf(UiState.Error::class.java)
        }
    }

    @Test
    fun `given view model when fetch movies with success then post ui state success`() {
        runTest {
            coEvery { useCase.searchMovies() } returns resultSuccess

            viewModel.fetchMovies(MovieType.MOVIES)

            val result = viewModel.fetchMovies.capture()

            assertThat(result).isInstanceOf(UiState.Success::class.java)
        }
    }

    @Test
    fun `given view model when fetch empty movies with success then post ui state empty`() {
        runTest {
            coEvery { useCase.searchMovies() } returns resultEmpty

            viewModel.fetchMovies(MovieType.MOVIES)

            val result = viewModel.fetchMovies.capture()

            assertThat(result).isInstanceOf(UiState.EmptyState::class.java)
        }
    }

    @Test
    fun `given view model when fetch tv series with error then post ui state error`() {
        runTest {
            coEvery { useCase.searchTvSeries() } returns resultError

            viewModel.fetchMovies(MovieType.SERIES)

            val result = viewModel.fetchMovies.capture()

            assertThat(result).isInstanceOf(UiState.Error::class.java)
        }
    }

    @Test
    fun `given view model when fetch tv series with success then post ui state success`() {
        runTest {
            coEvery { useCase.searchTvSeries() } returns resultSuccess

            viewModel.fetchMovies(MovieType.SERIES)

            val result = viewModel.fetchMovies.capture()

            assertThat(result).isInstanceOf(UiState.Success::class.java)
        }
    }

    @Test
    fun `given view model when fetch empty tv series with success then post ui state empty`() {
        runTest {
            coEvery { useCase.searchTvSeries() } returns resultEmpty

            viewModel.fetchMovies(MovieType.SERIES)

            val result = viewModel.fetchMovies.capture()

            assertThat(result).isInstanceOf(UiState.EmptyState::class.java)
        }
    }

    @Test
    fun `given view model when fetch favorite movies with error then post ui state error`() {
        runTest {
            coEvery { useCase.searchFavoriteMovies() } returns resultError

            viewModel.fetchMovies(MovieType.FAVORITES)

            val result = viewModel.fetchMovies.capture()

            assertThat(result).isInstanceOf(UiState.Error::class.java)
        }
    }

    @Test
    fun `given view model when fetch favorite movies with success then post ui state success`() {
        runTest {
            coEvery { useCase.searchFavoriteMovies() } returns resultSuccess

            viewModel.fetchMovies(MovieType.FAVORITES)

            val result = viewModel.fetchMovies.capture()

            assertThat(result).isInstanceOf(UiState.Success::class.java)
        }
    }

    @Test
    fun `given view model when fetch empty favorite movies with success then post ui state empty`() {
        runTest {
            coEvery { useCase.searchFavoriteMovies() } returns resultEmpty

            viewModel.fetchMovies(MovieType.FAVORITES)

            val result = viewModel.fetchMovies.capture()

            assertThat(result).isInstanceOf(UiState.EmptyState::class.java)
        }
    }
}
