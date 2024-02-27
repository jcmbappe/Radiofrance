package com.mbappe.radiofrance.shows

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.net.URLDecoder
import java.net.URLEncoder

private const val STATION_ID_ARG = "stationId"
private val URL_CHARACTER_ENCODING = Charsets.UTF_8.name()

internal class ShowsArgs(val stationId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[STATION_ID_ARG]),
                    URL_CHARACTER_ENCODING
                )
            )
}

fun showsScreenNavigationRoute(stationId: String) = "shows/$stationId"

fun NavController.navigateToShow(stationId: String) {
    val encodedId = URLEncoder.encode(stationId, URL_CHARACTER_ENCODING)
    navigate("topic_route/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.showsScreen(stationId: String) {
    composable(
        route = showsScreenNavigationRoute(stationId = stationId),
        arguments = listOf(
            navArgument(STATION_ID_ARG) { type = NavType.StringType },
        )
    ) {
        ShowsRoute()
    }
}