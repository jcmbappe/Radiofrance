package com.mbappe.radiofrance.ui.component.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbappe.radiofrance.ui.theme.RadioFranceTheme

@Composable
fun TagAtom(
    modifier: Modifier = Modifier,
    text: String,
    color: Color
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(color)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
            style = MaterialTheme.typography.labelMedium,
            text = text.uppercase()
        )
    }
}

@Preview
@Composable
private fun Preview() {
    RadioFranceTheme {
        TagAtom(text = "Musique Classique", color = Color.Green)
    }
}