package com.mbappe.radiofrance.shows

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mbappe.repositories.ShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    showsRepository: ShowsRepository,
    coroutineDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val stationArgs: ShowsArgs = ShowsArgs(savedStateHandle)
    private val stationId = stationArgs.stationId

    val flow = showsRepository.getShows(stationId = stationId, 30)
        .cachedIn(viewModelScope + coroutineDispatcher)
}