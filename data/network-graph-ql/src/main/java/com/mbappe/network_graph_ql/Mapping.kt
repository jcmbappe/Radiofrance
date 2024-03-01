package com.mbappe.network_graph_ql

import android.util.Log
import com.mbappe.models.Show
import com.mbappe.models.Station
import com.mbappe.radiofrance.GetBrandsQuery
import com.mbappe.radiofrance.GetShowsQuery
import com.mbappe.radiofrance.type.StationsEnum

fun GetBrandsQuery.Brand.toBrand() = Station(
    id = id,
    title = title,
    baseline = baseline,
    description = description,
    imageUrl = getImageUrl(id),
    colorHex = getColor(id)
).also {
    Log.d("JC", id)
}

fun GetShowsQuery.Node.toShow() = Show(
    id = id,
    title = title,
)

private fun getImageUrl(id: String) = when (id) {
    StationsEnum.FRANCEINTER.name -> "https://www.radiofrance.fr/client/immutable/assets/franceinter-rect.ItYoXmb4.svg"
    StationsEnum.FRANCEINFO.name -> "https://www.radiofrance.fr/client/immutable/assets/franceinfo-rect.j0cViiGg.svg"
    StationsEnum.FRANCEMUSIQUE.name -> "https://www.radiofrance.fr/client/immutable/assets/francemusique-rect.hN4xXE6s.svg"
    StationsEnum.FRANCECULTURE.name -> "https://www.radiofrance.fr/client/immutable/assets/franceculture-rect.KshWfknV.svg"
    StationsEnum.MOUV.name -> "https://www.radiofrance.fr/client/immutable/assets/mouv-rect.knW69Kwp.svg"
    StationsEnum.FIP.name -> "https://www.radiofrance.fr/client/immutable/assets/fip-rect.YkCCdkjh.svg"
    StationsEnum.FRANCEBLEU.name -> "https://www.radiofrance.fr/client/immutable/assets/francebleu-rect.-H1v00BQ.svg"
    else -> ""
}

private fun getColor(id: String) = when (id) {
    StationsEnum.FRANCEINTER.name -> 0xFFE20134
    StationsEnum.FRANCEINFO.name -> 0xFFFFC300
    StationsEnum.FRANCEMUSIQUE.name -> 0xFFA90042
    StationsEnum.FRANCECULTURE.name -> 0xFF762B84
    StationsEnum.MOUV.name -> 0xFF00FB8E
    StationsEnum.FIP.name -> 0xFFE2007A
    StationsEnum.FRANCEBLEU.name -> 0xFF0077D7
    else -> 0xFFFFFFFF
}