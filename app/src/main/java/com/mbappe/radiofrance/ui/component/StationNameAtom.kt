package com.mbappe.radiofrance.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mbappe.radiofrance.ui.theme.StationTextStyle

@Composable
fun stationNameAtom(modifier: Modifier = Modifier, text: String) {
    Text(
        text = text,
        style = StationTextStyle,
        modifier = modifier
    )
}

@Composable
@Preview
private fun preview() {
    stationNameAtom(text = "France Inter")
}