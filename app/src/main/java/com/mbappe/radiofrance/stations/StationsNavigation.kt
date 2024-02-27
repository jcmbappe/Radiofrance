package com.mbappe.radiofrance.stations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun stationsScreenNavigationRoute() = "stations"

fun NavGraphBuilder.stationsScreen(
    onStationClick: (stationId: String) -> Unit
) {
    composable(route = stationsScreenNavigationRoute()) {
        StationsRoute(onStationClick = onStationClick)
    }
}