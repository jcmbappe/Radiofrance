package com.mbappe.radiofrance.ui.component.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbappe.radiofrance.ui.component.atoms.ImageAtom

@Composable
fun ShowHeaderMolecule(
    imageRectangleUrl: String,
    modifier: Modifier = Modifier
) {

    val bottomShape =
        RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
    Box(modifier = modifier) {
        ImageAtom(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.75f),
            size = null,
            url = imageRectangleUrl
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .clip(bottomShape)
                .background(MaterialTheme.colorScheme.background)
                .align(Alignment.BottomCenter)
        )
    }
}
