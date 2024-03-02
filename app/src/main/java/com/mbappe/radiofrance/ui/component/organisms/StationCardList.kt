package com.mbappe.radiofrance.ui.component.organisms

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mbappe.models.Station
import com.mbappe.radiofrance.ui.component.molecules.StationMolecule

fun LazyListScope.stationsCardItems(
    items: List<Station>,
    onStationClick: (stationId: String) -> Unit
) = items(
    items = items,
    itemContent = { station ->
        StationMolecule(station = station, onStationClick = onStationClick)
        Spacer(modifier = Modifier.height(15.dp))
    }
)