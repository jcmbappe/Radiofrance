package com.mbappe.models


open class StationAssets(
    val logoSquareUrl: String,
    val logoRectangleUrl: String,
    val colorHex: Long
) {
     data object FranceInterAssets : StationAssets(
        logoSquareUrl = getAssetUrl("franceinter.q6kO3hHn.svg"),
        logoRectangleUrl = getAssetUrl("franceinter-rect.ItYoXmb4.svg"),
        colorHex = 0xFFE20134
    )
    data object FranceInfoAssets : StationAssets(
        logoSquareUrl = getAssetUrl("franceinfo.dx0Z7-75.svg"),
        logoRectangleUrl = getAssetUrl("franceinfo-rect.j0cViiGg.svg"),
        colorHex = 0xFFFFC300
    )
    data object FranceMusiqueAssets : StationAssets(
        logoSquareUrl = getAssetUrl("francemusique.wEE41QdE.svg"),
        logoRectangleUrl = getAssetUrl("francemusique-rect.hN4xXE6s.svg"),
        colorHex = 0xFFA90042
    )
    data object FranceCultureAssets : StationAssets(
        logoSquareUrl = getAssetUrl("franceculture.BZL7mpXp.svg"),
        logoRectangleUrl = getAssetUrl("franceculture-rect.KshWfknV.svg"),
        colorHex = 0xFF762B84
    )
    data object MouvAssets : StationAssets(
        logoSquareUrl = getAssetUrl("mouv.aXN0jVRz.svg"),
        logoRectangleUrl = getAssetUrl("mouv-rect.knW69Kwp.svg"),
        colorHex = 0xFF00FB8E
    )
    data object FipAssets : StationAssets(
        logoSquareUrl = getAssetUrl("fip.10Y249aI.svg"),
        logoRectangleUrl = getAssetUrl("fip-rect.YkCCdkjh.svg"),
        colorHex = 0xFFE2007A
    )
    data object FranceBleuAssets : StationAssets(
        logoSquareUrl = getAssetUrl("francebleu.75-hy66G.svg"),
        logoRectangleUrl = getAssetUrl("francebleu-rect.-H1v00BQ.svg"),
        colorHex = 0xFF0077D7
    )
    
    data object UnknownAssets : StationAssets(
        logoSquareUrl = "", 
        logoRectangleUrl = "", 
        colorHex = 0xFFFFFFFF
    )
}
private fun getAssetUrl(assetUrl: String) = "https://www.radiofrance.fr/client/immutable/assets/$assetUrl"
