package com.mbappe.radiofrance.stations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mbappe.models.Brand

@Composable
internal fun StationsRoute(
    modifier: Modifier = Modifier,
    stationsViewModel: StationsViewModel = hiltViewModel(),
) {
    val stationList by stationsViewModel.state.collectAsStateWithLifecycle()
    stationsScreen(
        stationList = stationList,
        modifier = modifier
    )
}

@Composable
private fun stationsScreen(
    stationList: List<Brand>,
    modifier: Modifier = Modifier
) {
    val state = rememberLazyListState()

    Box(modifier = modifier) {
        LazyColumn(
            state = state
        ) {
            items(items = stationList) { station ->
                Column {
                    Text(text = station.title)
                    station.baseline?.let { Text(text = it) }
                    station.description?.let { Text(text = it) }
                }
                HorizontalDivider(color = Color.Black)
            }
        }
    }
}