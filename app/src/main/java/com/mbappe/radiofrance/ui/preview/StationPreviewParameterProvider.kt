package com.mbappe.radiofrance.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mbappe.models.Station

class StationPreviewParameterProvider : PreviewParameterProvider<Station> {
    private val station = Station(
        id = "FRANCEINTER",
        title = "France Inter",
        baseline = "Le direct de France Inter",
        description = "Joyeuse, savante et populaire, France Inter est la radio généraliste de service public ",
        imageUrl = "https://www.radiofrance.fr/client/immutable/assets/franceinter-rect.ItYoXmb4.svg",
        colorHex = 0xFFE20134
    )

    override val values: Sequence<Station> = sequenceOf(
        station,
        station.copy(baseline = ""),
        station.copy(description = ""),
        station.copy(baseline = "", description = "")
    )
}