package br.com.jonathanarodr.playmovie.feature.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.jonathanarodr.playmovie.common.states.UiState
import br.com.jonathanarodr.playmovie.feature.domain.model.Movie
import br.com.jonathanarodr.playmovie.feature.domain.usecase.DetailUseCase
import br.com.jonathanarodr.playmovie.testing.captureState
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val movie = mockk<Movie>()
    private val useCase = mockk<DetailUseCase>()
    private val viewModel = DetailViewModel(useCase)

    @Test
    fun `given view model when insert favorite movie with error then post ui state error`() {
        coEvery { useCase.insertFavoriteMovie(movie) } returns Result.failure(mockk())

        viewModel.insertFavoriteMovie(movie)

        val result = viewModel.insertedMovie.captureState()

        assertThat(result).isInstanceOf(UiState.Error::class.java)
    }

    @Test
    fun `given view model when insert favorite movie with success then post ui state success`() {
        coEvery { useCase.insertFavoriteMovie(movie) } returns Result.success(mockk())

        viewModel.insertFavoriteMovie(movie)

        val result = viewModel.insertedMovie.captureState()

        assertThat(result).isInstanceOf(UiState.Success::class.java)
    }

    @Test
    fun `given view model when remove favorite movie with error then post ui state error`() {
        coEvery { useCase.removeFavoriteMovie(movie) } returns Result.failure(mockk())

        viewModel.removeFavoriteMovie(movie)

        val result = viewModel.removedMovie.captureState()

        assertThat(result).isInstanceOf(UiState.Error::class.java)
    }

    @Test
    fun `given view model when remove favorite movie with success then post ui state success`() {
        coEvery { useCase.removeFavoriteMovie(movie) } returns Result.success(mockk())

        viewModel.removeFavoriteMovie(movie)

        val result = viewModel.removedMovie.captureState()

        assertThat(result).isInstanceOf(UiState.Success::class.java)
    }
}
