package com.mbappe.radiofrance.shows

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mbappe.models.Show
import com.mbappe.repositories.ShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val showsRepository: ShowsRepository
) : ViewModel() {
    private val stationArgs: ShowsArgs = ShowsArgs(savedStateHandle)
    val stationId = stationArgs.stationId

    private val _state = MutableStateFlow<PagingData<Show>>(PagingData.empty())
    val state = _state

    init {
        viewModelScope.launch {
            showsRepository.getShows(stationId = stationId, 10)
                .cachedIn(viewModelScope)
                .onEach(_state::emit)
                .launchIn(this)
        }
    }
}