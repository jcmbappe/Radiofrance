package com.mbappe.radiofrance.ui.component.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.mbappe.models.Station
import com.mbappe.radiofrance.ui.component.atoms.TitleMediumAtom
import com.mbappe.radiofrance.ui.preview.StationPreviewParameterProvider

@Composable
fun StationMolecule(
    modifier: Modifier = Modifier,
    station: Station,
    onStationClick: (stationId: String) -> Unit,
) {
    val typography = MaterialTheme.typography

    CardWithImageMolecule(
        modifier = modifier,
        imageSquareUrl = station.stationAssets.logoSquareUrl,
        onCardClick = { onStationClick(station.id) }
    ) { imageSize, imageStartPadding ->
        Column(
            modifier = Modifier.padding(
                top = imageSize / 2 + 10.dp,
                start = imageStartPadding,
                end = imageStartPadding,
                bottom = 15.dp
            )
        ) {
            TitleMediumAtom(text = station.title)
            if (!station.baseline.isNullOrEmpty()) {
                station.baseline?.let {
                    Text(
                        style = typography.labelSmall,
                        text = it
                    )
                }
            }
            if (!station.description.isNullOrEmpty()) {
                station.description?.let {
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        style = typography.bodyMedium,
                        text = it
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RectangleShape)
                .background(Color(station.stationAssets.colorHex))

        )
    }
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(StationPreviewParameterProvider::class) station: Station
) {
    StationMolecule(station = station) {}
}