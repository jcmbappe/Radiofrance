@file:OptIn(ExperimentalLayoutApi::class)

package com.mbappe.radiofrance.stations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mbappe.models.Station
import com.mbappe.radiofrance.ui.component.organisms.stationsCardItems

@Composable
internal fun StationsRoute(
    onStationClick: (stationId: String) -> Unit,
    modifier: Modifier = Modifier,
    stationsViewModel: StationsViewModel = hiltViewModel(),
) {
    val stationList by stationsViewModel.state.collectAsStateWithLifecycle()
    stationsScreen(
        onStationClick = onStationClick,
        stationList = stationList,
        modifier = modifier
    )
}

@Composable
private fun stationsScreen(
    onStationClick: (stationId: String) -> Unit,
    stationList: List<Station>,
    modifier: Modifier = Modifier
) {
    val state = rememberLazyListState()

    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 15.dp),
            state = state
        ) {
            item {
                Spacer(
                    Modifier.windowInsetsTopHeight(
                        WindowInsets.statusBars
                    )
                )
            }
            stationsCardItems(items = stationList, onStationClick = onStationClick)
            item {
                Spacer(
                    Modifier.windowInsetsBottomHeight(
                        WindowInsets.systemBars
                    )
                )
            }
        }
    }
}