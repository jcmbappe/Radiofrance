package com.mbappe.radiofrance.stations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun stationsScreenNavigationRoute() = "stations"

fun NavGraphBuilder.stationsScreen() {
    composable(route = stationsScreenNavigationRoute()){
        StationsRoute()
    }
}