package com.mbappe.radiofrance.stations

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbappe.common.ApiResponse
import com.mbappe.models.Station
import com.mbappe.repositories.StationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StationsViewModel @Inject constructor(
    private val repository: StationsRepository
) : ViewModel() {

    private val _state = MutableStateFlow<List<Station>>(listOf())
    val state: StateFlow<List<Station>> = _state

    init {
        getStations()
    }

    private fun getStations() {
        viewModelScope.launch {
            repository.getStations()
                .onEach { apiResponse ->
                    when (apiResponse) {
                        is ApiResponse.Error -> Log.d("JC", "Error ${apiResponse.message}")
                        is ApiResponse.Success -> apiResponse.data?.let { _state.emit(it) }
                    }
                }.launchIn(this)
        }
    }
}