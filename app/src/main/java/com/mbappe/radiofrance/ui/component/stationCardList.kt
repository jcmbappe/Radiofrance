package com.mbappe.radiofrance.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mbappe.models.Station

fun LazyListScope.stationsCardItems(
    items: List<Station>,
    onStationClick: (stationId: String) -> Unit
) = items(
    items = items,
    itemContent = { station ->
        stationRow(station = station, onStationClick = onStationClick)
        Spacer(modifier = Modifier.height(15.dp))
    }
)