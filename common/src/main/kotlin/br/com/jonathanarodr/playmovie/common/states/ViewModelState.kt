package br.com.jonathanarodr.playmovie.common.states

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

interface StateHolder<State, Event> {
    val uiState: StateFlow<State>
    fun dispatchUiEvent(uiEvent: Event)
}

abstract class ViewModelState<State, Event> : ViewModel(), StateHolder<State, Event>
