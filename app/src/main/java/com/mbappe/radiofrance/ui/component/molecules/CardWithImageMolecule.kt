package com.mbappe.radiofrance.ui.component.molecules

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mbappe.radiofrance.ui.component.atoms.ImageAtom

@Composable
fun CardWithImageMolecule(
    modifier: Modifier = Modifier,
    imageSquareUrl: String,
    onCardClick: () -> Unit,
    content: @Composable ColumnScope.(imageSize: Dp, imageStartPadding: Dp) -> Unit
) {

    val imageHeight = 80.dp
    val contentPadding = 20.dp
    Box(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = imageHeight / 2)
                .wrapContentSize(),
            onClick = onCardClick,
        ) {
            content(imageHeight, contentPadding)
        }
        ImageAtom(
            modifier = Modifier
                .padding(start = contentPadding)
                .clip(CardDefaults.shape),
            size = DpSize(imageHeight, imageHeight),
            url = imageSquareUrl
        )
    }
}
