package com.mbappe.radiofrance.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mbappe.models.Station
import com.mbappe.radiofrance.ui.preview.StationPreviewParameterProvider

@Composable
fun stationRow(
    modifier: Modifier = Modifier,
    station: Station,
    onStationClick: (stationId: String) -> Unit,
) {
    val typography = MaterialTheme.typography

    val imageHeight = 60.dp
    val contentPadding = 20.dp
    Box(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = imageHeight / 2)
                .wrapContentSize(),
            onClick = { onStationClick(station.id) }
        ) {
            Column(
                modifier = Modifier.padding(
                    top = imageHeight / 2 + 10.dp,
                    start = contentPadding,
                    end = contentPadding,
                    bottom = 15.dp
                )
            ) {
                stationNameAtom(text = station.title)
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
            Box(modifier = Modifier.fillMaxWidth()
                .height(10.dp)
                .clip(RectangleShape)
                .background(Color(station.colorHex))

            )
        }
        ImageAtom(
            modifier = Modifier.padding(start = contentPadding)
                .clip(CardDefaults.shape),
            size = DpSize(imageHeight * 2, imageHeight),
            url = station.imageUrl
        )
    }
}

@Preview
@Composable
private fun preview(
    @PreviewParameter(StationPreviewParameterProvider::class) station: Station
) {
    stationRow(station = station) {}
}