package br.com.jonathanarodr.playmovie.common.states

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

interface StateHolder<State, Event> {
    val uiState: MutableStateFlow<State>
    fun dispatchUiEvent(uiEvent: Event)
}

abstract class ViewModelState<State, Event> : ViewModel(), StateHolder<State, Event>
