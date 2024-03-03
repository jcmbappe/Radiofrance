package com.mbappe.network_graph_ql

import com.mbappe.models.Personality
import com.mbappe.models.Podcast
import com.mbappe.models.Show
import com.mbappe.models.Station
import com.mbappe.models.StationAssets
import com.mbappe.models.Theme
import com.mbappe.network_graph_ql.mocked.FipAssets
import com.mbappe.network_graph_ql.mocked.FranceBleuAssets
import com.mbappe.network_graph_ql.mocked.FranceCultureAssets
import com.mbappe.network_graph_ql.mocked.FranceInfoAssets
import com.mbappe.network_graph_ql.mocked.FranceInterAssets
import com.mbappe.network_graph_ql.mocked.FranceMusiqueAssets
import com.mbappe.network_graph_ql.mocked.MouvAssets
import com.mbappe.network_graph_ql.mocked.UnknownAssets
import com.mbappe.radiofrance.GetBrandsQuery
import com.mbappe.radiofrance.GetShowsQuery
import com.mbappe.radiofrance.type.StationsEnum

internal fun GetBrandsQuery.Brand.toBrand() = Station(
    id = id,
    title = title,
    baseline = baseline,
    description = description,
    stationAssets = getAssetsByStationId(id),
)


internal fun GetShowsQuery.Node.toShow() = Show(
    id = id,
    title = title,
    url = url.orEmpty(),
    imageSquareUrl = "https://picsum.photos/seed/$id/400",
    podcasts = diffusionsConnection?.toList() ?: listOf(),
    themes = taxonomiesConnection?.toList() ?: listOf()
)

internal fun GetShowsQuery.DiffusionsConnection.toList(): List<Podcast>? =
    edges?.mapNotNull { diffusion ->
        diffusion?.node?.let { podcast ->
            Podcast(
                title = podcast.title,
                personalities = podcast.personalitiesConnection?.toList() ?: listOf()
            )
        }
    }

internal fun GetShowsQuery.PersonalitiesConnection.toList(): List<Personality>? =
    edges?.mapNotNull { personalitiesConnection ->
        personalitiesConnection?.node?.let { personality ->
            Personality(
                name = personality.name
            )
        }
    }

internal fun GetShowsQuery.TaxonomiesConnection.toList(): List<Theme> =
    edges?.mapNotNull { connection ->
        connection?.node?.let { theme ->
            if (!theme.title.isNullOrEmpty()) {
                Theme(
                    title = theme.title,
                    relation = connection.toThemeRelation()
                )
            } else null
        }
    } ?: listOf()


internal fun GetShowsQuery.Edge3.toThemeRelation() = when (relation) {
    "theme" -> Theme.Relation.THEME
    "subtheme" -> Theme.Relation.SUB_THEME
    else -> Theme.Relation.UNKNOWN
}

fun getAssetsByStationId(id: String): StationAssets = when (id) {
    StationsEnum.FRANCEINTER.name -> FranceInterAssets
    StationsEnum.FRANCEINFO.name -> FranceInfoAssets
    StationsEnum.FRANCEMUSIQUE.name -> FranceMusiqueAssets
    StationsEnum.FRANCECULTURE.name -> FranceCultureAssets
    StationsEnum.MOUV.name -> MouvAssets
    StationsEnum.FIP.name -> FipAssets
    StationsEnum.FRANCEBLEU.name -> FranceBleuAssets
    else -> UnknownAssets
}
