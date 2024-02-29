package com.mbappe.radiofrance.shows

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbappe.usecase.PaginatedShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    paginatedShowsUseCase: PaginatedShowsUseCase
) : ViewModel() {
    private val stationArgs: ShowsArgs = ShowsArgs(savedStateHandle)

    val pagingShowsFlow = paginatedShowsUseCase.getFlow(
        stationId = stationArgs.stationId,
        scope = viewModelScope
    )
}