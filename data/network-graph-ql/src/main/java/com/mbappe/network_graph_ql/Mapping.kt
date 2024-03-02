package com.mbappe.network_graph_ql

import com.mbappe.models.Personality
import com.mbappe.models.Podcast
import com.mbappe.models.Show
import com.mbappe.models.Station
import com.mbappe.models.StationAssets
import com.mbappe.models.Theme
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
    imageSquareUrl = "",
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
    StationsEnum.FRANCEINTER.name -> StationAssets.FranceInterAssets
    StationsEnum.FRANCEINFO.name -> StationAssets.FranceInfoAssets
    StationsEnum.FRANCEMUSIQUE.name -> StationAssets.FranceMusiqueAssets
    StationsEnum.FRANCECULTURE.name -> StationAssets.FranceCultureAssets
    StationsEnum.MOUV.name -> StationAssets.MouvAssets
    StationsEnum.FIP.name -> StationAssets.FipAssets
    StationsEnum.FRANCEBLEU.name -> StationAssets.FranceBleuAssets
    else -> StationAssets.UnknownAssets
}
