package com.mbappe.radiofrance.ui.component.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.mbappe.models.Show
import com.mbappe.radiofrance.ui.component.atoms.TagAtom
import com.mbappe.radiofrance.ui.component.atoms.TitleMediumAtom
import com.mbappe.radiofrance.ui.preview.ShowPreviewParameterProvider
import com.mbappe.radiofrance.ui.theme.RadioFranceTheme

@Composable
fun ShowCardMolecule(
    modifier: Modifier = Modifier,
    show: Show,
    stationColor: Color
) {
    val sortedThemes = show.themes.sortedBy { it.relation.ordinal }
    val typography = MaterialTheme.typography

    CardWithImageMolecule(
        modifier = modifier.fillMaxWidth(),
        imageSquareUrl = show.imageSquareUrl,
        onCardClick = { }
    ) { imageSize, imageStartPadding ->
        Column(
            modifier = Modifier.padding(
                top = imageSize / 2 + 10.dp,
                start = imageStartPadding,
                end = imageStartPadding,
                bottom = 15.dp
            )
        ) {
            TitleMediumAtom(
                modifier = Modifier.fillMaxWidth(),
                text = show.title
            )
            if (sortedThemes.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    for (themes in sortedThemes) {
                        TagAtom(text = themes.title, color = stationColor)
                    }
                }
            }
            if (show.podcasts.isNotEmpty()) {
                Column(Modifier.background(MaterialTheme.colorScheme.surfaceVariant)) {
                    Box(
                        modifier = Modifier
                            .clip(RectangleShape)
                            .height(5.dp)
                    )
                    val podcast = show.podcasts.first()

                    Text(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .fillMaxWidth(),
                        text = "Latest podcast :",
                        style = typography.labelLarge,
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 10.dp, start = 10.dp)
                            .fillMaxWidth(),
                        style = typography.titleSmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        text = podcast.title
                    )
                    if (podcast.personalities.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .padding(top = 5.dp, start = 10.dp)
                                .fillMaxWidth(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = typography.bodySmall,
                            text = podcast.personalities.joinToString { it.name }
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RectangleShape)
                .background(stationColor)

        )
    }
}

@Preview
@Composable
private fun preview(
    @PreviewParameter(ShowPreviewParameterProvider::class) show: Show
) {
    RadioFranceTheme {
        ShowCardMolecule(show = show, stationColor = Color.Green)
    }
}