package com.mbappe.radiofrance.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mbappe.models.Station
import com.mbappe.models.StationAssets

class StationPreviewParameterProvider : PreviewParameterProvider<Station> {
    private val station = Station(
        id = "FRANCEINTER",
        title = "France Inter",
        baseline = "Le direct de France Inter",
        description = "Joyeuse, savante et populaire, France Inter est la radio généraliste de service public ",
        stationAssets = StationAssets(
            logoSquareUrl = "", logoRectangleUrl = "", colorHex = 0xFFFF0000, loadingRes = 0

        )
    )

    override val values: Sequence<Station> = sequenceOf(
        station,
        station.copy(baseline = ""),
        station.copy(description = ""),
        station.copy(baseline = "", description = "")
    )
}