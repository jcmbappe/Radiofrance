package com.mbappe.radiofrance.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mbappe.models.Personality
import com.mbappe.models.Podcast
import com.mbappe.models.Show
import com.mbappe.models.Theme

class ShowPreviewParameterProvider : PreviewParameterProvider<Show> {
    private val show = Show(
        id = "erroribus",
        title = "MAXXI Classique",
        url = "",
        imageSquareUrl = "",
        podcasts = listOf(),
        themes = listOf()

    )

    private val podcast = Podcast(
        title = "Le journal intime d’un compositeur : Les Heures dolentes de Gabriel Dupont",
        personalities = listOf()
    )

    private val personality = Personality(
        name = "Felicia Clemons"
    )

    private val theme = Theme(title = "Musique", relation = Theme.Relation.THEME)
    private val themes =
        listOf(theme,
            Theme(title = "Musique Classique", relation = Theme.Relation.SUB_THEME),
            Theme(title = "Musique Rock", relation = Theme.Relation.SUB_THEME),
            Theme(title = "Musique Rap", relation = Theme.Relation.SUB_THEME),
        )

    override val values: Sequence<Show> = sequenceOf(
        show,
        show.copy(podcasts = listOf(podcast)),
        show.copy(
            podcasts = listOf(
                podcast.copy(personalities = listOf(personality, personality)),
                podcast
            )
        ),
        show.copy(
            podcasts = listOf(
                podcast.copy(personalities = listOf(personality, personality)),
                podcast
            ),
            themes = themes
        ),
        show.copy(
            podcasts = listOf(
                podcast.copy(personalities = listOf(personality, personality)),
                podcast
            ),
            themes = listOf(theme)
        ),
        show.copy(
            podcasts = listOf(
                podcast.copy(personalities = listOf(personality, personality)),
                podcast
            ),
            themes = themes
        ),
    )
}