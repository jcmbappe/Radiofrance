package com.mbappe.network_graph_ql

import com.mbappe.models.Show
import com.mbappe.models.Station
import com.mbappe.models.StationAssets
import com.mbappe.radiofrance.GetBrandsQuery
import com.mbappe.radiofrance.GetShowsQuery
import com.mbappe.radiofrance.type.StationsEnum

fun GetBrandsQuery.Brand.toBrand() = Station(
    id = id,
    title = title,
    baseline = baseline,
    description = description,
    stationAssets = getAssets(id),
)


fun GetShowsQuery.Node.toShow() = Show(
    id = id,
    title = title,
)

private fun getAssets(id: String): StationAssets = when (id) {
    StationsEnum.FRANCEINTER.name -> StationAssets.FranceInterAssets
    StationsEnum.FRANCEINFO.name -> StationAssets.FranceInfoAssets
    StationsEnum.FRANCEMUSIQUE.name -> StationAssets.FranceMusiqueAssets
    StationsEnum.FRANCECULTURE.name -> StationAssets.FranceCultureAssets
    StationsEnum.MOUV.name -> StationAssets.MouvAssets
    StationsEnum.FIP.name -> StationAssets.FipAssets
    StationsEnum.FRANCEBLEU.name -> StationAssets.FranceBleuAssets
    else -> StationAssets.UnknownAssets
}
