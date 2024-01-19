package br.com.jonathanarodr.playmovie.testing.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot

inline fun <reified T : Any> LiveData<T>.capture(): T {
    val observer = mockk<Observer<T>>()
    val state = slot<T>()

    every {
        observer.onChanged(
            capture(state)
        )
    } just runs

    this.observeForever(observer)

    return state.captured
}
