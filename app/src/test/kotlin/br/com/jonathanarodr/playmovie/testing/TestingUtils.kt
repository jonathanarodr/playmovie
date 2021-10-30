package br.com.jonathanarodr.playmovie.testing

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import br.com.jonathanarodr.playmovie.common.states.UiState
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot

inline fun <reified T : Any> LiveData<UiState<T>>.captureState(): UiState<T> {
    val observer = mockk<Observer<UiState<T>>>()
    val state = slot<UiState<T>>()

    every {
        observer.onChanged(
            capture(state)
        )
    } just runs

    this.observeForever(observer)

    return state.captured
}
